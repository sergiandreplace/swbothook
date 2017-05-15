package com.blindbugs.swbot.application.request;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Model class for <a href="https://docs.api.ai/docs/webhook#section-format-of-response-from-the-service"
 * >webhook response</a>.
 */
public class Fulfillment implements Serializable {

    @SerializedName("speech")
    public String speech;

    @SerializedName("messages")
    public List<ResponseMessage> messages;

    @SerializedName("displayText")
    public String displayText;

    @SerializedName("data")
    public Map<String, JsonElement> data;

    @SerializedName("source")
    public String source;

    @SerializedName("contextOut")
    public List<AIOutputContext> contextOut;

}
