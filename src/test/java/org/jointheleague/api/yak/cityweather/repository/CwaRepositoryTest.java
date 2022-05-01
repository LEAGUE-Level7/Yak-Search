package org.jointheleague.api.yak.cityweather.repository;

import org.jointheleague.api.yak.cityweather.repository.dto.WeatherInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CwaRepositoryTest {

    private CwaRepository cwar;

    @Mock
    WebClient webClientMock;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    WebClient.ResponseSpec responseSpecMock;

    @Mock
    Mono<WeatherInfo> cwaResponseMonoMock;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        cwar = new CwaRepository(webClientMock);
    }

    @Test
    void whenGetResults_thenReturnWeatherInfo(){
        //given
        String query = "San Diego";

        WeatherInfo weatherInfo = new WeatherInfo();

        WeatherInfo expectedResults = weatherInfo;

        when(webClientMock.get())
                .thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(WeatherInfo.class))
                .thenReturn(cwaResponseMonoMock);
        when(cwaResponseMonoMock.block())
                .thenReturn(weatherInfo);

        //when
        WeatherInfo actualResults = cwar.getResults(query);

        //then
        assertEquals(expectedResults, actualResults);
    }


}
