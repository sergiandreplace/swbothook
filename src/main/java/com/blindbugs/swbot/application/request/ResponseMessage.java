package com.blindbugs.swbot.application.request;

import com.google.gson.annotations.SerializedName;

/**
 * Base model class for
 * <a href="https://docs.api.ai/docs/query#section-message-objects">response message objects</a>.
 */
public class ResponseMessage {

    @SerializedName("type")
    public int type;
    @SerializedName("speech")
    public String speech;


}
