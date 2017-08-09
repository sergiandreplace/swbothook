package com.blindbugs.swbot.application.repository;

import com.blindbugs.swbot.application.datasource.swapi.models.ApiPeople;
import com.blindbugs.swbot.application.datasource.swapi.models.ApiSWModelList;
import com.blindbugs.swbot.application.datasource.swapi.sw.StarWarsApi;
import com.blindbugs.swbot.application.repository.mapper.PeopleMapper;
import com.blindbugs.swbot.domain.people.People;
import com.blindbugs.swbot.domain.people.PeopleCollection;
import com.blindbugs.swbot.domain.planet.PlanetCollection;
import java.util.List;
import javax.inject.Inject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class PlanetRepository implements PlanetCollection {
    private final StarWarsApi starWarsApi;

    @Inject
    public PlanetRepository(StarWarsApi starWarsApi) {
        this.starWarsApi = starWarsApi;
    }

    @Cacheable(cacheNames = "planet")
    @Override
    public String getPlanetName(int id) {
        return starWarsApi.getPlanet(id).name;
    }
}
