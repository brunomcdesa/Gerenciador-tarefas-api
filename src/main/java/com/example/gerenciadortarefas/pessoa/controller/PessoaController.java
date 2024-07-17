package com.example.gerenciadortarefas.pessoa.controller;

import com.example.gerenciadortarefas.configuracoes.PageRequest;
import com.example.gerenciadortarefas.pessoa.dto.PessoaFiltro;
import com.example.gerenciadortarefas.pessoa.dto.PessoaGastoResponse;
import com.example.gerenciadortarefas.pessoa.dto.PessoaRequest;
import com.example.gerenciadortarefas.pessoa.dto.PessoaResponse;
import com.example.gerenciadortarefas.pessoa.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pessoas")
public class PessoaController {

    private final PessoaService service;

    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public void salvar(@RequestBody @Valid PessoaRequest request) {
        service.salvar(request);
    }

    @GetMapping
    public Page<PessoaResponse> buscarTodos(PessoaFiltro filtro, PageRequest pageRequest) {
        return service.buscarTodos(filtro, pageRequest);
    }

    @GetMapping("{id}")
    public PessoaResponse buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody PessoaRequest request) {
        service.atualizar(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        service.remover(id);
    }

    @GetMapping("gastos")
    public PessoaGastoResponse buscarMediaHorasGastas() {
        return null;
    }
}
