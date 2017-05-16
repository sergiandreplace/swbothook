package com.blindbugs.swbot.application.config;

import com.blindbugs.swbot.application.datasource.swapi.sw.StarWarsApi;
import com.blindbugs.swbot.application.repository.PeopleRepository;
import com.blindbugs.swbot.application.repository.mapper.PeopleMapper;
import com.blindbugs.swbot.domain.people.FindPeopleService;
import com.blindbugs.swbot.domain.people.PeopleCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    PeopleCollection peopleCollection(StarWarsApi starWarsApi, PeopleMapper peopleMapper) {
        return new PeopleRepository(starWarsApi, peopleMapper);
    }

    @Bean
    FindPeopleService findPeopleService(PeopleCollection peopleCollection) {
        return new FindPeopleService(peopleCollection);
    }
}
