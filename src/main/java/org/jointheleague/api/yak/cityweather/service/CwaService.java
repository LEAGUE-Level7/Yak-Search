package org.jointheleague.api.yak.cityweather.service;

import org.jointheleague.api.yak.cityweather.repository.CwaRepository;
import org.jointheleague.api.yak.cityweather.repository.dto.WeatherInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CwaService {

    private final CwaRepository cwaRepository;

    public CwaService(CwaRepository cwaRepository){ this.cwaRepository = cwaRepository; }

    public WeatherInfo getResults(String query){
        return cwaRepository.getResults(query);
    }

}
