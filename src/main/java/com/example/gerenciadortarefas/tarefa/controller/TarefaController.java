package com.example.gerenciadortarefas.tarefa.controller;

import com.example.gerenciadortarefas.configuracoes.PageRequest;
import com.example.gerenciadortarefas.tarefa.dto.TarefaFiltro;
import com.example.gerenciadortarefas.tarefa.dto.TarefaRequest;
import com.example.gerenciadortarefas.tarefa.dto.TarefaResponse;
import com.example.gerenciadortarefas.tarefa.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tarefa")
public class TarefaController {

    private final TarefaService service;

    @PostMapping
    public void salvar(@RequestBody @Valid TarefaRequest request) {
        service.salvar(request);
    }

    @GetMapping
    public Page<TarefaResponse> listar(TarefaFiltro filtro, PageRequest pageRequest) {
        return service.listar(filtro, pageRequest);
    }
}
