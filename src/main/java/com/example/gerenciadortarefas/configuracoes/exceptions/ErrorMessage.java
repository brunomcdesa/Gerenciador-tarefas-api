package com.example.gerenciadortarefas.configuracoes.exceptions;

public record ErrorMessage(
        String message,
        String field
) {
    public static ErrorMessage of(String message) {
        return new ErrorMessage(message, null);
    }
}
