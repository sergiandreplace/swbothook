package com.blindbugs.swbot.application.action;

import com.blindbugs.swbot.application.request.AIRequest;
import org.springframework.context.annotation.Bean;


public class SubjectAction {


    public String getCharacter(AIRequest request) {
        String characterName = request.result.parameters.get("Subject");
        return  "{\n" +
                "  \"speech\": \"What do you want to know about " + characterName + "?\",\n" +
                "  \"displayText\": \"What do you want to know about " + characterName + "?\",\n" +
                "  \"source\": \"SWBot\",\n" +
                "  \"data\": {\"telegram\": \"What do you want to know about " + characterName + "?\"}\n" +
                "}";
    }
}
