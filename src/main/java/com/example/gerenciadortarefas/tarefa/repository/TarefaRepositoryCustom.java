package com.example.gerenciadortarefas.tarefa.repository;

import com.example.gerenciadortarefas.tarefa.dto.TarefaResponse;

import java.util.List;

public interface TarefaRepositoryCustom {

    List<TarefaResponse> findPendentes();
}
