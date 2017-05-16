package com.blindbugs.swbot.application.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Status implements Serializable {

    @SerializedName("code")
    public Integer code;
    @SerializedName("errorType")
    public String errorType;

    /**
     * Human readable error description.
     */
    @SerializedName("errorDetails")
    public String errorDetails;

    /**
     * Error unique ID. Use it in the requests to API.AI support.
     */
    @SerializedName("errorID")
    public String errorID;

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", errorType='" + errorType + '\'' +
                ", errorDetails='" + errorDetails + '\'' +
                ", errorID='" + errorID + '\'' +
                '}';
    }
}
