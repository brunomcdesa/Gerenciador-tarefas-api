package com.example.gerenciadortarefas.configuracoes.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
