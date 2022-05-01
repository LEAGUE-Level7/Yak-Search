package org.jointheleague.api.yak.cityweather.service;

import org.jointheleague.api.yak.cityweather.repository.CwaRepository;
import org.jointheleague.api.yak.cityweather.repository.dto.WeatherInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CwaServiceTest {

    CwaService cwas;

    @Mock
    CwaRepository cwar;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        cwas = new CwaService(cwar);
    }

    @Test
    void givenQuery_whenGetResults_thenReturnWeatherInfo(){
        //given
        String query = "San Diego";
        WeatherInfo weatherInfo = new WeatherInfo();

        WeatherInfo expectedResult = weatherInfo;

        when(cwar.getResults(query)).thenReturn(weatherInfo);

        //when
        WeatherInfo actualResult = cwas.getResults(query);

        //then
        assertEquals(expectedResult, actualResult);
    }

}
