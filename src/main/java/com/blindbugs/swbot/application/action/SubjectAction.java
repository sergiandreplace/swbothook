package com.blindbugs.swbot.application.action;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SubjectAction {
    private static final Logger logger = LoggerFactory.getLogger("HookAction");
    private static final String[] CHARACTERS = {"Luke Skywalker", "Leia Organa", "Darth Vader", "Han Solo"};

    public String getCharacter(String request) {
        Configuration configuration = Configuration.defaultConfiguration();
        configuration.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        String properCharacter = null;

        String characterName = JsonPath.using(configuration).parse(request).read("$.result.parameters.Subject");
        logger.info("Character name=" + characterName);

        int id = JsonPath.using(configuration).parse(request).read("$.originalRequest.data.message.chat.id");
        String conversationId = String.valueOf(id);
        logger.info("conversationId=" + conversationId);

        String action = JsonPath.using(configuration).parse(request).read( "$.result.action");
        logger.info("action=" + action);

        String response;
        if (characterName == null) {
            response = "Tell me the name of a character";
        } else {
            properCharacter = findCharacter(characterName);
            if (properCharacter != null) {
                if ("ask.subject.height".equalsIgnoreCase(action)) {
                    response = getHeightResponse(properCharacter);
                } else if ("ask.subject.hair".equalsIgnoreCase(action)) {
                    response = getHairColorResponse(properCharacter);
                } else if ("ask.subject.planet".equalsIgnoreCase(action)) {
                    response = getPlanetResponse(properCharacter);
                } else {
                    response = "What do you want to know about " + properCharacter + "?";
                }
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
