package org.jointheleague.api.yak.cityweather.presentation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CwaController {

    @GetMapping("/searchCityWeather")
    @ApiOperation(value = "Gets the weather at the city matching the search term.",
                    notes = "No Additional Notes",
                    response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result(s) found")}
    )
    public String getResults(@RequestParam(value="q") String query){
        return "Searching for weather in " + query + "...";
    }

}
