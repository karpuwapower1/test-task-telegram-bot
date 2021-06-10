package com.test.task.telegram.service;

import com.test.task.telegram.dto.CityDto;

import java.util.List;

public interface CityService {

    CityDto getCityById(long id);

    List<CityDto> getAll();

    CityDto getCityByName(String name);

    CityDto saveCity(CityDto cityDto);

    CityDto updateCity(long id, CityDto cityDto);
}
