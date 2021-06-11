package com.test.task.telegram.service.impl;

import com.test.task.telegram.dto.CityDto;
import com.test.task.telegram.exception.NoSuchCityException;
import com.test.task.telegram.mapper.CityMapper;
import com.test.task.telegram.repository.CityRepository;
import com.test.task.telegram.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityDto getCityByName(String name) {
        return cityRepository.findByNameIgnoreCase(name)
            .map(cityMapper::toCityDto)
            .orElseThrow(NoSuchCityException::new);
    }
}
