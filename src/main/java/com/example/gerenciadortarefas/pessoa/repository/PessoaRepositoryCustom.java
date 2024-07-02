package com.example.gerenciadortarefas.pessoa.repository;

import com.example.gerenciadortarefas.pessoa.model.Pessoa;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface PessoaRepositoryCustom {

    List<Pessoa> findAllByPredicate(Predicate predicate);
}
