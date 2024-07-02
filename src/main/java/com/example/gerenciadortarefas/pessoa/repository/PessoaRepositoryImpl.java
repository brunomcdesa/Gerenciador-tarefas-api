package com.example.gerenciadortarefas.pessoa.repository;

import com.example.gerenciadortarefas.pessoa.model.Pessoa;
import com.example.gerenciadortarefas.pessoa.model.QPessoa;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gerenciadortarefas.pessoa.model.QPessoa.pessoa;

@RequiredArgsConstructor
public class PessoaRepositoryImpl implements PessoaRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Pessoa> findAllByPredicate(Predicate predicate) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(pessoa)
                .where(predicate)
                .fetch();
    }
}
