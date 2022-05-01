package org.jointheleague.api.yak.cityweather.presentation;

import org.jointheleague.api.yak.cityweather.repository.dto.Condition;
import org.jointheleague.api.yak.cityweather.repository.dto.Current;
import org.jointheleague.api.yak.cityweather.repository.dto.Location;
import org.jointheleague.api.yak.cityweather.repository.dto.WeatherInfo;
import org.jointheleague.api.yak.cityweather.service.CwaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CwaControllerTest {
    CwaController cwac;

    @Mock
    CwaService cwas;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cwac = new CwaController(cwas);
    }

    @Test
    void givenGoodCityNameQuery_whenGetResults_thenReturnWeatherInfo() {
        //given
        String query = "San Diego";
        WeatherInfo weatherInfo = new WeatherInfo();
        Current current = new Current();
        Location location = new Location();
        Condition condition = new Condition();

        condition.setText("Sunny");

        current.setTempC(20.0);
        current.setTempF(20.0);
        current.setCondition(condition);
        current.setWindMph(5.0);
        current.setWindKph(5.0);
        current.setWindDegree(7);
        current.setWindDir("N");
        current.setPressureMb(90.0);
        current.setPressureIn(90.0);
        current.setPrecipMm(80.0);
        current.setPrecipIn(80.0);
        current.setHumidity(5);
        current.setCloud(5);
        current.setVisKm(6.0);
        current.setVisMiles(6.0);
        current.setUv(100.0);
        current.setGustMph(1.0);
        current.setGustKph(1.0);

        location.setName(query);
        location.setRegion("California");
        location.setCountry("USA");
        location.setLat(0.0);
        location.setLon(0.0);
        location.setTzId("idk");
        location.setLocaltime("8:00");

        weatherInfo.setCurrent(current);
        weatherInfo.setLocation(location);

        WeatherInfo expectedResult = weatherInfo;

        when(cwas.getResults(query)).thenReturn(weatherInfo);

        //when
        WeatherInfo actualResult = cwac.getResults(query);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void givenBadCityQuery_whenGetResults_thenThrowsException(){
        //given
        String query = "Sus Village";

        //when
        //then
        Throwable exceptionThrown = assertThrows(ResponseStatusException.class, () -> cwac.getResults(query));
        assertEquals(exceptionThrown.getMessage(), "404 NOT_FOUND \"Result(s) not found\"");
    }
}