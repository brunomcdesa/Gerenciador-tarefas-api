package com.example.gerenciadortarefas.pessoa.service;

import com.example.gerenciadortarefas.pessoa.dto.PessoaFiltro;
import com.example.gerenciadortarefas.pessoa.dto.PessoaRequest;
import com.example.gerenciadortarefas.pessoa.dto.PessoaResponse;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;
import com.example.gerenciadortarefas.pessoa.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public void salvar(PessoaRequest request) {
        repository.save(Pessoa.of(request.nome(), request.departamento()));
    }

    public List<PessoaResponse> buscarTodos(PessoaFiltro filtro) {
        return repository.findAll().stream()
                .map(PessoaResponse::of)
                .toList();
    }
}
