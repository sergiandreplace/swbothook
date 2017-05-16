package com.blindbugs.swbot.application.request;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Result implements Serializable {

    private static final long serialVersionUID = 1L;


    @SerializedName("action")
    public String action;

    @SerializedName("score")
    public float score;

    @SerializedName("source")
    public String source;

    /**
     * This field will be deserialized as hashMap container with all parameters and it's values
     */
    @SerializedName("parameters")
    public HashMap<String, String> parameters;

    /**
     * Currently active contexts
     */
    @SerializedName("contexts")
    public List<AIOutputContext> contexts;


    @SerializedName("metadata")
    public Metadata metadata;

    /**
     * The query that was used to produce this result
     */
    @SerializedName("resolvedQuery")
    public String resolvedQuery;

    /**
     * Fulfillment of the response
     */
    @SerializedName("fulfillment")
    public Fulfillment fulfillment;

    @SerializedName("actionIncomplete")
    public boolean actionIncomplete;

    @Override
    public String toString() {
        return "Result{" +
                "action='" + action + '\'' +
                ", score=" + score +
                ", source='" + source + '\'' +
                ", parameters=" + parameters +
                ", contexts=" + contexts +
                ", metadata=" + metadata +
                ", resolvedQuery='" + resolvedQuery + '\'' +
                ", fulfillment=" + fulfillment +
                ", actionIncomplete=" + actionIncomplete +
                '}';
    }
}
