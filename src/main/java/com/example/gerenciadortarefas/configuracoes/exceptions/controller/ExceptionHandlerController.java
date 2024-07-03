package com.example.gerenciadortarefas.configuracoes.exceptions.controller;

import com.example.gerenciadortarefas.configuracoes.exceptions.ErrorMessage;
import com.example.gerenciadortarefas.configuracoes.exceptions.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage handleNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage());
        return ErrorMessage.of(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorMessage> handleBeanValidationException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        var result = ex instanceof MethodArgumentNotValidException res
                ? res.getBindingResult()
                : ex.getBindingResult();

        return result.getFieldErrors().stream()
                .map(error -> new ErrorMessage(
                        String.format("O campo %s %s", error.getField(), error.getDefaultMessage()),
                        error.getField()))
                .toList();
    }
}

