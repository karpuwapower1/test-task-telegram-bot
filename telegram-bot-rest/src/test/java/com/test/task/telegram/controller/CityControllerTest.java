package com.test.task.telegram.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.test.task.telegram.BaseIntegrationTest;
import com.test.task.telegram.generatorHelper.CityGenerator;
import com.test.task.telegram.repository.CityRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CityControllerTest extends BaseIntegrationTest {

    private static final String CITIES_URL = "/cities";

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void getAllCitiesTest() throws Exception {
        mockMvc.perform(get(CITIES_URL))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
    }

    @Test
    public void getCityByIdTest() throws Exception {
        val city = cityRepository.save(CityGenerator.generateCity());

        mockMvc.perform(get(CITIES_URL + "/" + city.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.id").value(city.getId()))
            .andExpect(jsonPath("$.name").value(city.getName()))
            .andExpect(jsonPath("$.info").value(city.getInfo()));
    }

    @Test
    public void getCityByIdWhenCityNotPresentTest() throws Exception {
        mockMvc.perform(get(CITIES_URL + "/-1"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void saveCityTest() throws Exception {
        val city = CityGenerator.generateCity();

        mockMvc.perform(post(CITIES_URL, city)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new JsonMapper().writeValueAsString(city))
        ).andExpect(status().isCreated())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.name").value(city.getName()))
            .andExpect(jsonPath("$.info").value(city.getInfo()));

        cityRepository.delete(city);
    }

    @Test
    public void updateCityTest() throws Exception {
        val city = cityRepository.save(CityGenerator.generateCity());
        val updateCity = CityGenerator.generateCityDto();

        mockMvc.perform(put(CITIES_URL + "/" + city.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new JsonMapper().writeValueAsString(updateCity))
        ).andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.id").value(city.getId()))
            .andExpect(jsonPath("$.name").value(updateCity.getName()))
            .andExpect(jsonPath("$.info").value(updateCity.getInfo()));

        cityRepository.deleteById(city.getId());
    }

    @Test
    public void deleteCityTest() throws Exception {
        val city = cityRepository.save(CityGenerator.generateCity());

        mockMvc.perform(delete(CITIES_URL + "/" + city.getId()))
            .andExpect(status().isNoContent());

        assertFalse(cityRepository.findById(city.getId()).isPresent());
    }
}
