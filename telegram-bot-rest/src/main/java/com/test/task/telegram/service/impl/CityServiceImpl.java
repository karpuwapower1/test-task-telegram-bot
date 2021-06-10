package com.test.task.telegram.service.impl;

import com.test.task.telegram.exception.NoSuchCityException;
import com.test.task.telegram.dto.CityDto;
import com.test.task.telegram.mapper.CityMapper;
import com.test.task.telegram.repository.CityRepository;
import com.test.task.telegram.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityDto getCityById(long id) {
        return cityRepository.findById(id)
            .map(cityMapper::toCityDto)
            .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<CityDto> getAll() {
        return cityRepository.findAll()
            .stream()
            .map(cityMapper::toCityDto)
            .collect(Collectors.toList());
    }

    @Override
    public CityDto getCityByName(String name) {
        return cityRepository.findByNameIgnoreCase(name)
            .map(cityMapper::toCityDto)
            .orElseThrow(NoSuchCityException::new);
    }

    @Override
    public CityDto saveCity(CityDto cityDto) {
        val city = cityMapper.toCity(cityDto);
        return cityMapper.toCityDto(cityRepository.save(city));
    }

    @Override
    public CityDto updateCity(long id, CityDto cityDto) {
        val updated = cityRepository.findById(id)
            .map(city -> cityMapper.updateCity(cityDto, city))
            .orElseThrow(IllegalArgumentException::new);
        return cityMapper.toCityDto(cityRepository.save(updated));
    }
}
