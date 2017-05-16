package com.blindbugs.swbot.application.repository.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Mapper<SOURCE, TARGET> {
    public abstract TARGET map(SOURCE source);

    public List<TARGET> map(List<SOURCE> sources) {
        return sources.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    protected Integer getId(String url) {
        return Integer.valueOf(url.substring(url.substring(0, url.lastIndexOf("/")-1).lastIndexOf("/")+1,url.lastIndexOf("/")));
    }

    protected List<Integer> getIds(List<String> urls) {
        return urls.stream()
                .map(this::getId)
                .collect(Collectors.toList());
    }
}
