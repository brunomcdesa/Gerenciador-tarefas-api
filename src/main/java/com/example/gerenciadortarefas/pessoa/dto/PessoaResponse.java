package com.example.gerenciadortarefas.pessoa.dto;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;

public record PessoaResponse(
        String nome,
        EDepartamento departamento,
        String horasGastas) {

    public static PessoaResponse of(Pessoa pessoa, String horasGastas) {
        return new PessoaResponse(pessoa.getNome(), pessoa.getDepartamento(), horasGastas);
    }
}
