package com.example.gerenciadortarefas.pessoa.controller;

import com.example.gerenciadortarefas.pessoa.dto.PessoaFiltro;
import com.example.gerenciadortarefas.pessoa.dto.PessoaRequest;
import com.example.gerenciadortarefas.pessoa.dto.PessoaResponse;
import com.example.gerenciadortarefas.pessoa.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pessoas")
public class PessoaController {

    private final PessoaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void salvar(@RequestBody @Valid PessoaRequest request) {
        service.salvar(request);
    }

    @GetMapping
    public List<PessoaResponse> buscarTodos(PessoaFiltro filtro) {
        return service.buscarTodos(filtro);
    }

}
