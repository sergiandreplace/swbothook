package com.blindbugs.swbot.application.config;

import com.blindbugs.swbot.application.datasource.swapi.sw.APIConstants;
import com.blindbugs.swbot.application.datasource.swapi.sw.StarWarsApi;
import com.blindbugs.swbot.application.datasource.swapi.sw.StarWarsOkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit.RestAdapter;

@Configuration
public class SWApiConfiguration {

    @Bean
    StarWarsApi starWarsApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new StarWarsOkClient())
                .setEndpoint(APIConstants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return restAdapter.create(StarWarsApi.class);
    }
}
