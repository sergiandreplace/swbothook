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

        String characterName = JsonPath.read(request, "$.result.parameters.Subject");
        logger.info("Character name=" + characterName);

        String conversationId = JsonPath.read(request, "$.result.contexts[0].parameters.telegram_chat_id");
        logger.info("conversationId=" + conversationId);

        String response;
        if (characterName == null) {
            response = "Tell me the name of a character";
        } else {
            String properCharacter = findCharacter(characterName);
            if (properCharacter != null) {
                response = "What do you want to know about " + properCharacter + "?";
            } else {
                response = "I don't know who " + characterName + " is";
            }
        }

        return "{\n" +
                "  \"speech\": \"" + response + "\",\n" +
                "  \"displayText\": \"" + response + "\",\n" +
                "  \"source\": \"SWBot\",\n" +
                "  \"data\": {\n" +
                "    \"telegram\": {\n" +
                "      \"chat_id\":\"" + conversationId + "\",\n" +
                "      \"text\":\"" + response + "\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
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
