package com.blindbugs.swbot.application.repository.mapper;

import com.blindbugs.swbot.application.datasource.swapi.models.ApiPeople;
import com.blindbugs.swbot.domain.people.People;

public class PeopleMapper extends Mapper<ApiPeople, People> {
    @Override
    public People map(ApiPeople apiPeople) {
        People people = new People.Builder()
                .name(apiPeople.name)
                .birthYear(apiPeople.birthYear)
                .created(apiPeople.created)
                .edited(apiPeople.edited)
                .films(getIds(apiPeople.filmsUrls))
                .gender(apiPeople.gender)
                .hairColor(apiPeople.hairColor)
                .height(apiPeople.height)
                .homeWorld(getId(apiPeople.homeWorldUrl))
                .mass(apiPeople.mass)
                .skinColor(apiPeople.skinColor)
                .species(getIds(apiPeople.speciesUrls))
                .starShips(getIds(apiPeople.starshipsUrls))
                .vehicles(getIds(apiPeople.vehiclesUrls))
                .build();
        ;

        return people;
    }

}
