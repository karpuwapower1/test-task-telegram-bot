package com.test.task.telegram.generatorHelper;

import com.test.task.telegram.dto.CityDto;
import com.test.task.telegram.entity.City;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CityGenerator {

    public City generateCity() {
        return new City("name", "info");
    }

    public CityDto generateCityDto() {
        return CityDto.builder()
            .name("cityDtoName")
            .info("cityDtoInfo")
            .build();
    }
}
