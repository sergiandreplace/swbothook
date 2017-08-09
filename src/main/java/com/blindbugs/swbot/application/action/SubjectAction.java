package com.blindbugs.swbot.application.action;

import com.blindbugs.swbot.domain.movies.Movies;
import com.blindbugs.swbot.domain.people.FindPeopleService;
import com.blindbugs.swbot.domain.people.People;
import com.blindbugs.swbot.domain.planet.PlanetCollection;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;


public class SubjectAction {
    private static final Logger logger = LoggerFactory.getLogger("HookAction");
    public static final String PATH_REQUEST_SUBJECT = "$.result.parameters.Subject";
    public static final String PATH_REQUEST_TELEGRAM_CONVERSATION_ID = "$.originalRequest.data.message.chat.id";
    public static final String PATH_REQUEST_ACTIONS_CONVERSATION_ID = "$.originalRequest.data.conversation.conversationId";
    public static final String PATH_REQUEST_ACTIONS_CONVERSATION_TOKEN = "$.originalRequest.data.conversation.conversationToken";
    public static final String PATH_ACTION = "$.result.action";


    private FindPeopleService findPeopleService;
    private PlanetCollection planetCollection;


    public SubjectAction(FindPeopleService findPeopleService, PlanetCollection planetCollection) {
        this.findPeopleService = findPeopleService;
        this.planetCollection = planetCollection;
    }

    public String getCharacter(String request) {
        Configuration configuration = Configuration.defaultConfiguration();
        configuration.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS, Option.AS_PATH_LIST);
        String properCharacter = null;

        String characterName = JsonPath.using(configuration).parse(request).read(PATH_REQUEST_SUBJECT);
        logger.info("Character name=" + characterName);
        String id;
        String conversationId;
        String token = "";
        Source source;
        try {
            id = String.valueOf(JsonPath.using(configuration).parse(request).read(PATH_REQUEST_TELEGRAM_CONVERSATION_ID));
            conversationId = String.valueOf(id);
            source = Source.telegram;
            logger.info("conversationId=" + conversationId);
        } catch (PathNotFoundException e) {
            try {
                id = JsonPath.using(configuration).parse(request).read(PATH_REQUEST_ACTIONS_CONVERSATION_ID);
                token  = "";//JsonPath.using(configuration).parse(request).read(PATH_REQUEST_ACTIONS_CONVERSATION_TOKEN);
                conversationId = String.valueOf(id);
                source = Source.actions;
            } catch (PathNotFoundException ex) {
                conversationId = null;
                source = null;
            }
        }

        String action = JsonPath.using(configuration).parse(request).read(PATH_ACTION);
        logger.info("action=" + action);

        String response;
        if (characterName == null) {
            response = "Tell me the name of a character";
        } else {
            List<People> peopleList = findPeopleService.execute(characterName);
            if (peopleList.size() == 1) {
                properCharacter = peopleList.get(0).getName();
                response = getResponse(peopleList.get(0), action);
            } else if (peopleList.size() > 1) {
                response = "Do you mean " + peopleList.subList(0, peopleList.size() - 1).stream()
                        .map(People::getName)
                        .collect(Collectors.joining(", "))
                        + " or " + peopleList.get(peopleList.size() - 1).getName();
            } else {
                response = "I don't know who " + characterName + " is";
            }
        }
        String contextOut = properCharacter != null ? "  \"contextOut\":[{\"name\":\"out\",\"Subject\":\"" + properCharacter + "\"}],\n" : "";
        if (source == Source.telegram) {
            return "{\n" +
                "  \"speech\": \"" + response + "\",\n" +
                "  \"displayText\": \"" + response + "\",\n" +
                "  \"source\": \"SWBot\",\n" +
                contextOut +
                "  \"data\": {\n" +
                "    \"telegram\": {\n" +
                "      \"chat_id\":\"" + conversationId + "\",\n" +
                "      \"text\":\"" + response + "\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        } else if (source == Source.actions) {
            return "{\n" +
                "  \"speech\": \"" + response + "\",\n" +
                "  \"displayText\": \"" + response + "\",\n" +
                "  \"source\": \"SWBot\",\n" +
                "" +
                contextOut +

                "  \"data\": {\n" +
                "    \"google\": {\n" +
                "      \"conversationToken\" : \"" + token + "\",\n" +
                "      \"expectUserResponse\" : true,\n" +
                "      \"expectedInputs\" : {\n" +
                "        \"inputPrompt\" : {\n" +
                "          \n" +
                "        },\n" +
                "        \"possibleIntents\": {\n" +
                "          \"richInitialPrompt\": {\n" +
                "            \"items\": {\n" +
                "              \"simpleResponse\" : {\n" +
                "                \"textToSpeech\" : \"" + response +"\"\n" +
                "              }\n" +
                "            }\n" +
                "          },\n" +
                "          \"noInputPrompts\" : {\"textToSpeech\": \"hellloooo\"}\n" +
                "        }\n" +
                "      },\n" +
                "      \"isInSandbox\" : true\n" +
                "    }\n" +
                "  }}";
        } else {
                return "{\n" +
                    "  \"speech\": \"" + response + "\",\n" +
                    "  \"displayText\": \"" + response + "\",\n" +
                    "  \"source\": \"SWBot\",\n" +
                    contextOut + "}";
        }
    }

    private String getResponse(People person, String action) {
        String response;
        Random rand = new Random();
        String gender = person.getGender();
        String pronoun = (gender.equalsIgnoreCase("female")) ? "she" : "he";
        String subject = (rand.nextBoolean()) ? pronoun : person.getName();
        switch (action) {
            case "subject.set":
                String[] subjectAnswers = new String[] {
                    "What do you want to know about %1$s?",
                    "Ok, what information do you want about %1$s?",
                    "Cool one, I know a lot of things about %1$s, what do you want to know?"
                };
                response = String.format(subjectAnswers[rand.nextInt(subjectAnswers.length)], person.getName());
                break;
            case "subject.get.height":
                String[] heightAnswers = new String[] {
                    "%1$s is %2$s cm tall",
                    "%1$s measures %2$s cm"
                };
                response = String.format(heightAnswers[rand.nextInt(heightAnswers.length)], subject, person.getHeight());
                break;
            case "subject.get.hair":
                String[] hairAnswers = new String[] {
                    "%1$s's hair color is %2$s",
                    "%1$s's has a %2$s hair",
                    "%1$s's hair is %2$s"
                };
                response = String.format(hairAnswers[rand.nextInt(hairAnswers.length)], person.getName(), person.getHairColor());
                break;
            case "subject.get.planet":
                String [] planetAnswers = new String[] {
                    "%1$s was born in %2$s",
                    "%1$s is from %2$s",
                    "%1$s comes from %2$s"
                };
                response = String.format(planetAnswers[rand.nextInt(planetAnswers.length)], subject, planetCollection.getPlanetName(person.getHomeWorld()));
                break;
            case "subject.get.movies":
                String [] moviesAnswers = new String[] {
                    "%1$s was in %2$s",
                    "%1$s appeared in %2$s",
                    "%1$s appears in the movies %2$s"
                };
                response = String.format(moviesAnswers[rand.nextInt(moviesAnswers.length)],  subject, Movies.getMovies(person.getFilms()));
                break;
            default:
                response = "I don't know that about " + subject;
        }
        return response;
    }

    private enum Source {
        telegram,
        actions
    }
}
