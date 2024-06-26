package com.example.gerenciadortarefas.pessoa.dto;

import com.example.gerenciadortarefas.configuracoes.PredicateBase;

import static com.example.gerenciadortarefas.pessoa.model.QPessoa.pessoa;

public class PessoaPredicate extends PredicateBase {

    public PessoaPredicate comId(Integer id) {
        if (id != null) {
            builder.and(pessoa.id.eq(id));
        }
        return this;
    }
}
