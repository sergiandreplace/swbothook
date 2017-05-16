package com.blindbugs.swbot.application.action;

import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SubjectAction {
    private static final Logger logger = LoggerFactory.getLogger("HookAction");


    public String getCharacter(String request) {
        String characterName = JsonPath.read(request, "$.result.parameters.Subject");
        logger.info("Character name=" + characterName);

        String conversationId = JsonPath.read(request, "$.result.contexts[0].parameters.telegram_chat_id");
        logger.info("conversationId=" + conversationId);

        return "{\n" +
                "  \"speech\": \"What do you want to know about " + characterName + "?\",\n" +
                "  \"displayText\": \"What do you want to know about " + characterName + "?\",\n" +
                "  \"source\": \"SWBot\",\n" +
                "  \"data\": {\n" +
                "    \"telegram\": {\n" +
                "      \"chat_id\":\"" + conversationId + "\",\n" +
                "      \"text\":\"What do you want to know about " + characterName + "?\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }
}
