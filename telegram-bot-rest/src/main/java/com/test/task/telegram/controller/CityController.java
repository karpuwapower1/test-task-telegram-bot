package com.test.task.telegram.controller;

import com.test.task.telegram.dto.CityDto;
import com.test.task.telegram.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityDto> getAllCities() {
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public CityDto getCityById(@PathVariable("id") long id) {
        return cityService.getCityById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto saveCity(@RequestBody CityDto cityDto) {
        return cityService.saveCity(cityDto);
    }

    @PutMapping(value = "/{id}")
    public CityDto updateCity(@PathVariable("id") long id,
                              @RequestBody CityDto cityDto) {
        return cityService.updateCity(id, cityDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable("id") long id) {
        cityService.deleteCity(id);
    }
}
