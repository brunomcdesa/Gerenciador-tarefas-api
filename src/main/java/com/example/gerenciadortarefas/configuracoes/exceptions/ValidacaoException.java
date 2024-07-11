package com.example.gerenciadortarefas.configuracoes.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidacaoException extends RuntimeException {

    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
