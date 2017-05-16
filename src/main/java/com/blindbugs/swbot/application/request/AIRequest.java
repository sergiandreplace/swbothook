package com.blindbugs.swbot.application.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class AIRequest implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * Unique identifier of the result.
     */
    @SerializedName("id")
    public String id;

    @SerializedName("timestamp")
    public Date timestamp;
    
    @SerializedName("lang")
    public String lang;

    /**
     * Result object
     */
    @SerializedName("result")
    public Result result;

    @SerializedName("status")
    public Status status;
    
    @SerializedName("sessionId")
    public String sessionId;


    @Override
    public String toString() {
        return "AIRequest{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", lang='" + lang + '\'' +
                ", result=" + result +
                ", status=" + status +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
