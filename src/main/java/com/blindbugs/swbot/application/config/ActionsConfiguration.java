package com.blindbugs.swbot.application.config;

import com.blindbugs.swbot.application.action.SubjectAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActionsConfiguration {

    @Bean
    SubjectAction subjectAction() {
        return new SubjectAction();
    }
}
