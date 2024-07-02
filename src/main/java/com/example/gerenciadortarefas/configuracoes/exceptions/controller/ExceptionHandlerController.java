package com.example.gerenciadortarefas.configuracoes.exceptions.controller;

import com.example.gerenciadortarefas.configuracoes.exceptions.NotFoundException;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public List<Mensagem> notFound(NotFoundException ex) {
        return singletonList(new Mensagem(ex.getMessage()));
    }

    @Data
    private static class Mensagem {
        private String mensagem;

        public Mensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }
}

