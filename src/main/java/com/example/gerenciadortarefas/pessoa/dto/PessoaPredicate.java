package com.example.gerenciadortarefas.pessoa.dto;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.configuracoes.PredicateBase;

import static com.example.gerenciadortarefas.pessoa.model.QPessoa.pessoa;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class PessoaPredicate extends PredicateBase {

    public PessoaPredicate comId(Integer id) {
        if (id != null) {
            builder.and(pessoa.id.eq(id));
        }
        return this;
    }

    public PessoaPredicate comNome(String nome) {
        if (isNotBlank(nome)) {
            builder.and(pessoa.nome.containsIgnoreCase(nome));
        }
        return this;
    }

    public PessoaPredicate comDepartado(EDepartamento departamento) {
        if (departamento != null) {
            builder.and(pessoa.departamento.eq(departamento));
        }
        return this;
    }
}
