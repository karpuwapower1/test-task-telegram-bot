package com.test.task.telegram.service.impl;

import com.test.task.telegram.dto.AdviceDto;
import com.test.task.telegram.mapper.AdviceMapper;
import com.test.task.telegram.repository.AdviceRepository;
import com.test.task.telegram.service.AdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdviceServiceImpl implements AdviceService {

    private final AdviceRepository adviceRepository;
    private final AdviceMapper adviceMapper;

    @Override
    public List<AdviceDto> findAll() {
        return adviceMapper.toAdviceDto(adviceRepository.findAll());
    }

    @Override
    public List<AdviceDto> getAllByCityName(String cityName) {
        return adviceMapper.toAdviceDto(adviceRepository.findAllByCity_NameIgnoreCase(cityName));
    }
}
