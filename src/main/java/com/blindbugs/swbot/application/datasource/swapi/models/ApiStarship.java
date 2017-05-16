package com.blindbugs.swbot.application.datasource.swapi.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Oleur on 22/12/2014.
 * ApiStarship model represents a single transport craft that has hyperdrive capability.
 */
public class ApiStarship extends ApiVehicle implements Serializable {

    @SerializedName("starship_class")
    public String starshipClass;

    @SerializedName("hyperdrive_rating")
    public String hyperdriveRating;

    @SerializedName("MGLT")
    public String mglt;

}
