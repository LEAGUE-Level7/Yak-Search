package org.jointheleague.api.yak.cityweather.repository;

import org.jointheleague.api.yak.cityweather.repository.dto.WeatherInfo;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class CwaRepository {

    private final WebClient webClient;

    private static final String baseURL = "http://api.weatherapi.com/v1/current.json";

    public CwaRepository(){
        webClient = WebClient
                .builder()
                .baseUrl(baseURL)
                .build();
    }

    public WeatherInfo getResults(String query){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", System.getenv("API_KEY"))
                        .queryParam("q", query)
                        .build()
                )
                .retrieve()
                .bodyToMono(WeatherInfo.class)
                .block();
    }

}
