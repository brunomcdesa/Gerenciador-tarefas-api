package com.example.gerenciadortarefas.tarefa.controller;

import com.example.gerenciadortarefas.configuracoes.PageRequest;
import com.example.gerenciadortarefas.tarefa.dto.TarefaFiltro;
import com.example.gerenciadortarefas.tarefa.dto.TarefaRequest;
import com.example.gerenciadortarefas.tarefa.dto.TarefaResponse;
import com.example.gerenciadortarefas.tarefa.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tarefa")
public class TarefaController {

    private final TarefaService service;

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public void salvar(@RequestBody @Valid TarefaRequest request) {
        service.salvar(request);
    }

    @GetMapping
    public Page<TarefaResponse> listar(TarefaFiltro filtro, PageRequest pageRequest) {
        return service.listar(filtro, pageRequest);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping("{id}/alocar")
    public void alocar(@PathVariable Integer id) {
        service.alocar(id);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping("{id}/finalizar")
    public void finalizar(@PathVariable Integer id) {
        service.finalizar(id);
    }

}
