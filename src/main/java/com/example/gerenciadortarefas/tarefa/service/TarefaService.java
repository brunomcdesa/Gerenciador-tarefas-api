package com.example.gerenciadortarefas.tarefa.service;

import com.example.gerenciadortarefas.configuracoes.PageRequest;
import com.example.gerenciadortarefas.configuracoes.exceptions.NotFoundException;
import com.example.gerenciadortarefas.configuracoes.exceptions.ValidacaoException;
import com.example.gerenciadortarefas.pessoa.service.PessoaService;
import com.example.gerenciadortarefas.tarefa.dto.TarefaFiltro;
import com.example.gerenciadortarefas.tarefa.dto.TarefaRequest;
import com.example.gerenciadortarefas.tarefa.dto.TarefaResponse;
import com.example.gerenciadortarefas.tarefa.model.Tarefa;
import com.example.gerenciadortarefas.tarefa.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;
    private final PessoaService pessoaService;

    public void salvar(TarefaRequest request) {
        repository.save(Tarefa.of(request));
    }

    public Page<TarefaResponse> listar(TarefaFiltro filtro, PageRequest pageRequest) {
        return repository.findAll(filtro.toPredicate().build(), pageRequest).map(TarefaResponse::of);
    }

    @Transactional
    public void alocar(Integer id) {
        var tarefa = findById(id);
        var pessoas = pessoaService.findByDepartamento(tarefa.getDepartamento())
                .orElseThrow(() -> new ValidacaoException("Não existem pessoas neste departamento."));
        tarefa.setPessoaAlocada(pessoas);
        repository.save(tarefa);
    }

    private Tarefa findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar a tarefa."));
    }
}
