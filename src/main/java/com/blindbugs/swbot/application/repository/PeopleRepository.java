package com.blindbugs.swbot.application.repository;

import com.blindbugs.swbot.application.datasource.swapi.models.ApiPeople;
import com.blindbugs.swbot.application.datasource.swapi.models.ApiSWModelList;
import com.blindbugs.swbot.application.datasource.swapi.sw.StarWarsApi;
import com.blindbugs.swbot.application.repository.mapper.PeopleMapper;
import com.blindbugs.swbot.domain.people.People;
import com.blindbugs.swbot.domain.people.PeopleCollection;

import javax.inject.Inject;
import java.util.List;

public class PeopleRepository implements PeopleCollection {
    private final StarWarsApi starWarsApi;
    private final PeopleMapper peopleMapper;

    @Inject
    public PeopleRepository(StarWarsApi starWarsApi, PeopleMapper peopleMapper) {
        this.starWarsApi = starWarsApi;
        this.peopleMapper = peopleMapper;
    }

    @Override
    public People getPeople(int id) {
        ApiPeople apiPeople = starWarsApi.getPeople(id);
        return peopleMapper.map(apiPeople);
    }

    @Override
    public List<People> findPeople(String name) {
        ApiSWModelList<ApiPeople> peoplePage = starWarsApi.findPeople(name);
        return peopleMapper.map(peoplePage.results);

    }
}
