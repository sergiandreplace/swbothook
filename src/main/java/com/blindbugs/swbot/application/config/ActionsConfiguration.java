package com.blindbugs.swbot.application.config;

import com.blindbugs.swbot.application.action.SubjectAction;
import com.blindbugs.swbot.domain.people.FindPeopleService;
import com.blindbugs.swbot.domain.planet.PlanetCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActionsConfiguration {

    @Bean
    SubjectAction subjectAction(FindPeopleService findPeopleService, PlanetCollection planetCollection) {
        return new SubjectAction(findPeopleService, planetCollection);
    }


}
