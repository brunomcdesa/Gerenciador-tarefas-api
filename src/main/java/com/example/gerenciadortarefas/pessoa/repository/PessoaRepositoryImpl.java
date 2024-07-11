package com.example.gerenciadortarefas.pessoa.repository;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.example.gerenciadortarefas.pessoa.model.QPessoa.pessoa;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
public class PessoaRepositoryImpl implements PessoaRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Optional<Pessoa> findByDepartamento(EDepartamento departamento) {
        return ofNullable(new JPAQueryFactory(entityManager)
                .selectFrom(pessoa)
                .where(pessoa.departamento.eq(departamento))
                .orderBy(pessoa.tarefas.size().asc())
                .fetchFirst());
    }
}
