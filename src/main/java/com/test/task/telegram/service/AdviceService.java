package com.test.task.telegram.service;

import com.test.task.telegram.dto.AdviceDto;

import java.util.List;

public interface AdviceService {

    List<AdviceDto> findAll();

    List<AdviceDto> getAllByCityName(String cityName);
}
