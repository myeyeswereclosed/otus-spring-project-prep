package ru.otus.project.gateway.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.project.gateway.service.user.InvalidCredentials;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidCredentials.class)
    @ResponseStatus(UNAUTHORIZED)
    public void handle(InvalidCredentials e) {}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public void handle(Exception e) {}
}
