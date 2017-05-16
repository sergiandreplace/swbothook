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
    private static final String[] CHARACTERS = {"Luke Skywalker", "Leia Organa", "Darth Vader", "Han Solo"};
    private FindPeopleService findPeopleService;

    public SubjectAction(FindPeopleService findPeopleService) {

        this.findPeopleService = findPeopleService;
    }

    public String getCharacter(String request) {
        Configuration configuration = Configuration.defaultConfiguration();
        configuration.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS, Option.AS_PATH_LIST);
        String properCharacter = null;

        String characterName = JsonPath.using(configuration).parse(request).read("$.result.parameters.Subject");
        logger.info("Character name=" + characterName);
        int id;
        String conversationId;
        try {
            id = JsonPath.using(configuration).parse(request).read("$.originalRequest.data.message.chat.id");
            conversationId = String.valueOf(id);
            logger.info("conversationId=" + conversationId);
        } catch (PathNotFoundException e) {
            conversationId = null;
        }

        String action = JsonPath.using(configuration).parse(request).read("$.result.action");
        logger.info("action=" + action);

        String response;
        if (characterName == null) {
            response = "Tell me the name of a character";
        } else {
            List<People> peopleList = findPeopleService.execute(characterName);
            if (peopleList.size() == 1) {
                response = "What do you want to know about " + peopleList.get(0).getName() + "?";
                properCharacter = peopleList.get(0).getName();
            } else if (peopleList.size() > 1) {
                response = "Do you mean " + peopleList.subList(0, peopleList.size() - 1).stream()
                        .map(People::getName)
                        .collect(Collectors.joining(", "))
                        + " or " + peopleList.get(peopleList.size() - 1).getName();
            } else {
                response = "I don't know who " + characterName + " is";
            }
        }
        String contextOut = properCharacter != null ? "  \"contextOut\":[{\"Subject\":\"" + properCharacter + "\"}],\n" : "";
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

    private String getHairColorResponse(String properCharacter) {
        String hairColor;
        switch (properCharacter) {
            case "Luke Skywalker":
                hairColor = " is blonde";
                break;
            case "Leia Organa":
                hairColor = " has two dark cupcakes in her head";
                break;
            case "Darth Vader":
                hairColor = " was blonde, now is bald as a fish";
                break;
            case "Han Solo":
                hairColor = " has a nice brown hair";
                break;
            default:
                hairColor = " has probably no hair";
                break;

        }
        return properCharacter + hairColor;
    }

    private String getPlanetResponse(String properCharacter) {
        String planet;
        switch (properCharacter) {
            case "Luke Skywalker":
                planet = " was born in space";
                break;
            case "Leia Organa":
                planet = " was born in a Spaceship";
                break;
            case "Darth Vader":
                planet = " was born in Tatooine";
                break;
            case "Han Solo":
                planet = " planet is a mistery";
                break;
            default:
                planet = " was not born anywhere";
                break;

        }
        return properCharacter + planet;
    }

    private String getHeightResponse(String properCharacter) {
        String height;
        switch (properCharacter) {
            case "Luke Skywalker":
                height = "1.70";
                break;
            case "Leia Organa":
                height = "1.60";
                break;
            case "Darth Vader":
                height = "2.10";
                break;
            case "Han Solo":
                height = "1.85";
                break;
            default:
                height = "0.5";
                break;

        }
        return properCharacter + " is " + height + " meters tall";
    }

    private String findCharacter(String unknownCharacter) {
        if (unknownCharacter == null) {
            return null;
        }
        for (String character : CHARACTERS) {
            if (character.equalsIgnoreCase(unknownCharacter.trim())) {
                return character;
            }
        }
        return null;
    }
}
