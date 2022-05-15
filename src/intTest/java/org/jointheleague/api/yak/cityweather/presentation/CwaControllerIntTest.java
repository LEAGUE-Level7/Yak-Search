package org.jointheleague.api.yak.cityweather.presentation;

import org.jointheleague.api.yak.cityweather.repository.dto.Condition;
import org.jointheleague.api.yak.cityweather.repository.dto.Current;
import org.jointheleague.api.yak.cityweather.repository.dto.Location;
import org.jointheleague.api.yak.cityweather.repository.dto.WeatherInfo;
import org.jointheleague.api.yak.cityweather.service.CwaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CwaController.class)
public class CwaControllerIntTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CwaService cwaService;

    @Test
    public void givenGoodQuery_whenSearchForResults_thenIsOkAndReturnsResults() throws Exception {
        //given
        String query = "San Diego";
        WeatherInfo weatherInfo = new WeatherInfo();
        Current current = new Current();
        Location location = new Location();
        Condition condition = new Condition();

        String conditionText = "Sunny";
        condition.setText(conditionText);

        double tempC = 20.0;
        double tempF = 20.0;
        double windMph = 5.0;
        double windKph = 5.0;
        int windDegree = 7;
        String windDir = "N";
        double pressureMb = 90.0;
        double pressureIn = 90.0;
        double precipMm = 80.0;
        double precipIn = 80.0;
        int humidity = 5;
        int cloud = 5;
        double visKm = 6.0;
        double visMiles = 6.0;
        double uv = 100.0;
        double gustMph = 1.0;
        double gustKph = 1.0;

        current.setTempC(tempC);
        current.setTempF(tempF);
        current.setCondition(condition);
        current.setWindMph(windMph);
        current.setWindKph(windKph);
        current.setWindDegree(windDegree);
        current.setWindDir(windDir);
        current.setPressureMb(pressureMb);
        current.setPressureIn(pressureIn);
        current.setPrecipMm(precipMm);
        current.setPrecipIn(precipIn);
        current.setHumidity(humidity);
        current.setCloud(cloud);
        current.setVisKm(visKm);
        current.setVisMiles(visMiles);
        current.setUv(uv);
        current.setGustMph(gustMph);
        current.setGustKph(gustKph);

        String cityName = query;
        String region = "California";
        String country = "USA";
        double lat = 0.0;
        double lon = 0.0;
        String tzId = "idk";
        String localtime = "8:00";

        location.setName(cityName);
        location.setRegion(region);
        location.setCountry(country);
        location.setLat(lat);
        location.setLon(lon);
        location.setTzId(tzId);
        location.setLocaltime(localtime);

        weatherInfo.setCurrent(current);
        weatherInfo.setLocation(location);

        WeatherInfo expectedResult = weatherInfo;

        when(cwaService.getResults(query)).thenReturn(weatherInfo);

        //when

        //then
        MvcResult mvcResult = mockMvc.perform(get("/searchCityWeather?q=" + query))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location.name", is(cityName)))
                .andExpect(jsonPath("$.location.region", is(region)))
                .andExpect(jsonPath("$.location.lat", is(lat)))
                .andExpect(jsonPath("$.location.lon", is(lon)))
                .andExpect(jsonPath("$.location.tz_id", is(tzId)))
                .andExpect(jsonPath("$.location.localtime", is(localtime)))
                .andExpect(jsonPath("$.current.condition.text", is(conditionText)))
                .andExpect(jsonPath("$.current.temp_c", is(tempC)))
                .andExpect(jsonPath("$.current.temp_f", is(tempF)))
                .andExpect(jsonPath("$.current.wind_mph", is(windMph)))
                .andExpect(jsonPath("$.current.wind_kph", is(windKph)))
                .andExpect(jsonPath("$.current.wind_degree", is(windDegree)))
                .andExpect(jsonPath("$.current.wind_dir", is(windDir)))
                .andExpect(jsonPath("$.current.pressure_mb", is(pressureMb)))
                .andExpect(jsonPath("$.current.pressure_in", is(pressureIn)))
                .andExpect(jsonPath("$.current.precip_mm", is(precipMm)))
                .andExpect(jsonPath("$.current.precip_in", is(precipIn)))
                .andExpect(jsonPath("$.current.humidity", is(humidity)))
                .andExpect(jsonPath("$.current.cloud", is(cloud)))
                .andExpect(jsonPath("$.current.vis_km", is(visKm)))
                .andExpect(jsonPath("$.current.vis_miles", is(visMiles)))
                .andExpect(jsonPath("$.current.uv", is(uv)))
                .andExpect(jsonPath("$.current.gust_mph", is(gustMph)))
                .andExpect(jsonPath("$.current.gust_kph", is(gustKph)))
                .andReturn();
        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
    }

    @Test
    public void giveBadQuery_whenSearchForResults_thenIsNotFound() throws Exception{
        //given
        String query = "ajlflsfak";

        //when
        //then
        mockMvc.perform(get("/searchCityWeather?q= " + query))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
