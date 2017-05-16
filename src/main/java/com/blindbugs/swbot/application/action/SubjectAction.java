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

        String characterName = JsonPath.read(request, "$.result.parameters.Subject");
        logger.info("Character name=" + characterName);

        String conversationId = JsonPath.read(request, "$.result.contexts[0].parameters.telegram_chat_id");
        logger.info("conversationId=" + conversationId);

        String action = JsonPath.read(request, "$.result.action");

        String response;
        if (characterName == null) {
            response = "Tell me the name of a character";
        } else {
            properCharacter = findCharacter(characterName);
            if (properCharacter != null) {
                if ("ask.subject.height".equalsIgnoreCase(action)) {
                    response = getHeightResponse(properCharacter);
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
