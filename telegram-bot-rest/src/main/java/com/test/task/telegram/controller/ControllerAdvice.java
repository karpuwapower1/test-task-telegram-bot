package com.test.task.telegram.controller;


import com.test.task.telegram.dto.ErrorDto;
import com.test.task.telegram.exception.NoSuchCityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoSuchCityException.class)
    public ErrorDto handleNotFoundException(HttpServletRequest request, RuntimeException exception) {
        return handle(request, exception);
    }

    private ErrorDto handle(HttpServletRequest request, Exception exception) {
        log.error("Exception at " + request.getRequestURI(), exception);
        return new ErrorDto(exception);
    }
}
