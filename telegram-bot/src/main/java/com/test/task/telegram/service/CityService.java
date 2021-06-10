package com.test.task.telegram.service;

import com.test.task.telegram.dto.CityDto;

import java.util.List;

public interface CityService {
    
    CityDto getCityByName(String name);
}
