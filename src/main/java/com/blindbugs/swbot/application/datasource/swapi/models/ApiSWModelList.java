package com.blindbugs.swbot.application.datasource.swapi.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Oleur on 22/12/2014.
 * ApiFilm list model
 */
public class ApiSWModelList<T> implements Serializable {
    public int count;
    public String next;
    public String previous;
    public List<T> results;

    public boolean hasMore() {
        return (next != null && next.length() != 0);
    }
}
