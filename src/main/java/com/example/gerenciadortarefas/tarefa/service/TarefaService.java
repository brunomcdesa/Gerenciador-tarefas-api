package com.example.gerenciadortarefas.tarefa.service;

import com.example.gerenciadortarefas.configuracoes.PageRequest;
import com.example.gerenciadortarefas.tarefa.dto.TarefaFiltro;
import com.example.gerenciadortarefas.tarefa.dto.TarefaRequest;
import com.example.gerenciadortarefas.tarefa.dto.TarefaResponse;
import com.example.gerenciadortarefas.tarefa.model.Tarefa;
import com.example.gerenciadortarefas.tarefa.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

    public void salvar(TarefaRequest request) {
        repository.save(Tarefa.of(request));
    }

    public Page<TarefaResponse> listar(TarefaFiltro filtro, PageRequest pageRequest) {
        return repository.findAll(filtro.toPredicate().build(), pageRequest).map(TarefaResponse::of);
    }
}
