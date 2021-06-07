package com.test.task.telegram.controller;

import com.test.task.telegram.dto.CityDto;
import com.test.task.telegram.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities")
    public List<CityDto> getAllCities() {
        return cityService.findAll();
    }

    @GetMapping("/cities/{id}")
    public CityDto getCityByUuid(@PathVariable UUID id) {
        return cityService.findCityById(id);
    }
}
