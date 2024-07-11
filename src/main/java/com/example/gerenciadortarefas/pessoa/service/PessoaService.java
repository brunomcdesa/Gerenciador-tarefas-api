package com.example.gerenciadortarefas.pessoa.service;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.configuracoes.PageRequest;
import com.example.gerenciadortarefas.configuracoes.exceptions.NotFoundException;
import com.example.gerenciadortarefas.pessoa.dto.PessoaFiltro;
import com.example.gerenciadortarefas.pessoa.dto.PessoaRequest;
import com.example.gerenciadortarefas.pessoa.dto.PessoaResponse;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;
import com.example.gerenciadortarefas.pessoa.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public void salvar(PessoaRequest request) {
        repository.save(Pessoa.of(request.nome(), request.departamento()));
    }

    public Page<PessoaResponse> buscarTodos(PessoaFiltro filtro, PageRequest pageRequest) {
        return repository.findAll(filtro.toPredicate(), pageRequest).map(PessoaResponse::of);
    }

    public PessoaResponse buscarPorId(Integer id) {
        return PessoaResponse.of(findById(id));
    }

    public void atualizar(Integer id, PessoaRequest request) {
        var pessoa = findById(id);
        pessoa.atualizarDados(request);
        repository.save(pessoa);
    }

    private Pessoa findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada."));
    }

    public void remover(Integer id) {
        repository.deleteById(id);
        log.info("Pessoa {} removida com sucesso!", id);
    }

    public Optional<Pessoa> findByDepartamento(EDepartamento departamento) {
        return repository.findByDepartamento(departamento);
    }
}
