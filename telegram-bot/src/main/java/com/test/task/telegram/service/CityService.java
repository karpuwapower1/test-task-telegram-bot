package com.test.task.telegram.service;

import com.test.task.telegram.dto.CityDto;

public interface CityService {

    CityDto getCityByName(String name);
}
