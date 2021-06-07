package com.test.task.telegram.service.impl;

import com.test.task.telegram.dto.CityDto;
import com.test.task.telegram.mapper.CityMapper;
import com.test.task.telegram.repository.CityRepository;
import com.test.task.telegram.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityDto findCityById(UUID uuid) {
        return cityRepository.findByUuid(uuid.toString())
            .map(cityMapper::toCityDto)
            .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<CityDto> findAll() {
        return cityRepository.findAll()
            .stream()
            .map(cityMapper::toCityDto)
            .collect(Collectors.toList());
    }
}
