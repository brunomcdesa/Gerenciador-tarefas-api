package com.example.gerenciadortarefas.pessoa.repository;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static com.example.gerenciadortarefas.pessoa.model.QPessoa.pessoa;
import static com.example.gerenciadortarefas.tarefa.model.QTarefa.tarefa;
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

    @Override
    public Optional<Pessoa> findByNomeAndPeriodo(String nome, LocalDate dataInicial, LocalDate dataFinal) {
        return Optional.ofNullable(new JPAQueryFactory(entityManager)
                .selectFrom(pessoa)
                .leftJoin(pessoa.tarefas, tarefa).fetchJoin()
                .where(tarefa.pessoaAlocada.nome.equalsIgnoreCase(nome)
                        .and(tarefa.prazo.between(dataFinal.atStartOfDay(),
                                dataFinal.atTime(LocalTime.MAX))))
                .fetchFirst());
    }
}
