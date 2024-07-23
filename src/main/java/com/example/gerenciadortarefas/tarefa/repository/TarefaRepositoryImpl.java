package com.example.gerenciadortarefas.tarefa.repository;

import com.example.gerenciadortarefas.comum.enums.EBoolean;
import com.example.gerenciadortarefas.tarefa.dto.TarefaResponse;

import com.example.gerenciadortarefas.tarefa.model.QTarefa;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gerenciadortarefas.tarefa.model.QTarefa.tarefa;

@RequiredArgsConstructor
public class TarefaRepositoryImpl implements TarefaRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<TarefaResponse> findPendentes() {
        return new JPAQueryFactory(entityManager)
            .select(Projections.constructor(TarefaResponse.class,
                tarefa.titulo,
                tarefa.descricao,
                tarefa.departamento,
                tarefa.prazo,
                tarefa.duracao,
                tarefa.finalizado,
                tarefa.pessoaAlocada.isNull().when(true).then(EBoolean.F).otherwise(EBoolean.V)))
            .from(tarefa)
            .where(tarefa.pessoaAlocada.isNull())
            .orderBy(tarefa.prazo.asc())
            .limit(3L)
            .fetch();
    }
}
