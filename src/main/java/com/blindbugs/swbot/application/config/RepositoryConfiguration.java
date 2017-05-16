package com.blindbugs.swbot.application.config;

import com.blindbugs.swbot.application.repository.mapper.PeopleMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    PeopleMapper peopleMapper() {
        return new PeopleMapper();
    }

}
