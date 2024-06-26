package com.example.gerenciadortarefas.pessoa.dto;

public record PessoaFiltro (
        Integer id
) {


    public PessoaPredicate toPredicate() {
        return new PessoaPredicate()
                .comId(id);
    }
}
