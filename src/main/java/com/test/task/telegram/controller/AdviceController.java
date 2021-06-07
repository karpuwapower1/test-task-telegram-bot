package com.test.task.telegram.controller;

import com.test.task.telegram.dto.AdviceDto;
import com.test.task.telegram.service.AdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/advices")
@RequiredArgsConstructor
public class AdviceController {

    private final AdviceService adviceService;

    @GetMapping
    public List<AdviceDto> getAllAdvices() {
        return adviceService.findAll();
    }
}
