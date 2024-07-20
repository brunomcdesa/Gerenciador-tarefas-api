package com.example.gerenciadortarefas.pessoa.repository;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;

import java.time.LocalDate;
import java.util.Optional;

public interface PessoaRepositoryCustom {

    Optional<Pessoa> findByDepartamento(EDepartamento departamento);

    Optional<Pessoa> findByNomeAndPeriodo(String nome, LocalDate dataInicial, LocalDate dataFinal);
}
