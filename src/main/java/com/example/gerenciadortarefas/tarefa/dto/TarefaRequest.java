package com.example.gerenciadortarefas.tarefa.dto;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.configuracoes.exceptions.ValidacaoException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record TarefaRequest(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime prazo,
        @NotNull
        EDepartamento departamento,
        @NotNull
        LocalTime duracao
) {

    public void validarPrazo() {
        if (this.prazo.isBefore(LocalDateTime.now())) {
            throw new ValidacaoException("O prazo deve ser maior que a data atual.");
        }
    }
}
