package com.blindbugs.swbot.application.action;

import com.blindbugs.swbot.domain.people.FindPeopleService;
import com.blindbugs.swbot.domain.people.People;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;


public class SubjectAction {
    private static final Logger logger = LoggerFactory.getLogger("HookAction");
    public static final String PATH_REQUEST_SUBJECT = "$.result.parameters.Subject";
    public static final String PATH_REQUEST_CONVERSATION_ID = "$.originalRequest.data.message.chat.id";
    public static final String PATH_ACTION = "$.result.action";
    private FindPeopleService findPeopleService;

    public SubjectAction(FindPeopleService findPeopleService) {
        this.findPeopleService = findPeopleService;
    }

    public String getCharacter(String request) {
        Configuration configuration = Configuration.defaultConfiguration();
        configuration.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS, Option.AS_PATH_LIST);
        String properCharacter = null;

        String characterName = JsonPath.using(configuration).parse(request).read(PATH_REQUEST_SUBJECT);
        logger.info("Character name=" + characterName);
        int id;
        String conversationId;
        try {
            id = JsonPath.using(configuration).parse(request).read(PATH_REQUEST_CONVERSATION_ID);
            conversationId = String.valueOf(id);
            logger.info("conversationId=" + conversationId);
        } catch (PathNotFoundException e) {
            conversationId = null;
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
    }

    private String getResponse(People person, String action) {
        String response="";
        switch (action) {
            case "subject.set":
                response = "What do you want to know about " + person.getName() + "?";
                break;
            case "subject.get.height":
                response = person.getName() + " is " + person.getHeight() + "cm tall ";
                break;
            case "subject.get.hair":
                response = person.getName() + "'s hair color is " + person.getHairColor();
                break;
            default:
                response = "I don't know that about " + person.getName();
        }
        return response;
    }


}
