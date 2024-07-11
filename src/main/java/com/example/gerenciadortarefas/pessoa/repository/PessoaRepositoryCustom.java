package com.example.gerenciadortarefas.pessoa.repository;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;

import java.util.Optional;

public interface PessoaRepositoryCustom {

    Optional<Pessoa> findByDepartamento(EDepartamento departamento);
}
