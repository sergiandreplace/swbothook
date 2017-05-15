package com.blindbugs.swbot.application.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Metadata implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Name of the intent that produced this result
     */
    @SerializedName("intentName")
    public String intentName;

    /**
     * Id of the intent that produced this result
     */
    @SerializedName("intentId")
    public String intentId;
    
    /**
     * Indicates wheather webhook functionaly is enabled in the triggered intent.
     */
    @SerializedName("webhookUsed")
    public String webhookUsed;

}
