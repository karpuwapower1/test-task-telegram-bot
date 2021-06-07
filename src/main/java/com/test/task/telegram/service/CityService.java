package com.test.task.telegram.service;

import com.test.task.telegram.dto.CityDto;

import java.util.List;
import java.util.UUID;

public interface CityService {

    CityDto findCityById(UUID uuid);

    List<CityDto> findAll();
}
