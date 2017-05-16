package com.blindbugs.swbot.application.request;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class AIOutputContext implements Serializable {

	private static final long serialVersionUID = 1L;

    @SerializedName("name")
    public String name;

    @SerializedName("parameters")
    public Map<String, JsonElement> parameters;

    @SerializedName("lifespan")
    public Integer lifespan;

    @Override
    public String toString() {
        return "AIOutputContext{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", lifespan=" + lifespan +
                '}';
    }
}
