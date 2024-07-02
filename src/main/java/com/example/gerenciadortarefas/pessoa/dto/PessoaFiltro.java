package com.example.gerenciadortarefas.pessoa.dto;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.querydsl.core.types.Predicate;

public record PessoaFiltro(
        Integer id,
        String nome,
        EDepartamento departamento
) {

    public Predicate toPredicate() {
        return new PessoaPredicate()
                .comId(id)
                .comNome(nome)
                .comDepartado(departamento)
                .build();
    }
}
