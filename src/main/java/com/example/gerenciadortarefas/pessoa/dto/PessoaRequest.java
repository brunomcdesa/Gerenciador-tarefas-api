package com.example.gerenciadortarefas.pessoa.dto;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import jakarta.validation.constraints.NotBlank;

public record PessoaRequest(
        @NotBlank String nome,
        EDepartamento departamento) {
}
