package org.jointheleague.api.yak.cityweather.presentation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jointheleague.api.yak.cityweather.repository.dto.WeatherInfo;
import org.jointheleague.api.yak.cityweather.service.CwaService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CwaController {
    private final CwaService cwaService;

    public CwaController(CwaService cwaService){
        this.cwaService = cwaService;
    }

    @GetMapping("/searchCityWeather")
    @CrossOrigin()
    @ApiOperation(value = "Gets the current weather at the city matching the search term.",
                    notes = "Returns an object containing the requested weather information.",
                    response = WeatherInfo.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result(s) found"),
            @ApiResponse(code = 404, message = "Result(s) not found")
    })
    public WeatherInfo getResults(@RequestParam(value="q") String query){
        WeatherInfo results = cwaService.getResults(query);
        if(results == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result(s) not found");
        }
        return results;
    }

}
