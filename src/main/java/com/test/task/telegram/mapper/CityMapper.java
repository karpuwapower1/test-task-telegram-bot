package com.test.task.telegram.mapper;

import com.test.task.telegram.dto.CityDto;
import com.test.task.telegram.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

    CityDto toCityDto(City city);

    @Mapping(target = "id", ignore = true)
    City toCity(CityDto cityDto);

    @Mapping(target = "id", ignore = true)
    City updateCity(CityDto cityDto, @MappingTarget City city);
}
