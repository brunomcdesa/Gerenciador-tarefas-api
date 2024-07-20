package com.example.gerenciadortarefas.pessoa.service;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.configuracoes.PageRequest;
import com.example.gerenciadortarefas.configuracoes.exceptions.NotFoundException;
import com.example.gerenciadortarefas.pessoa.dto.PessoaFiltro;
import com.example.gerenciadortarefas.pessoa.dto.PessoaGastoResponse;
import com.example.gerenciadortarefas.pessoa.dto.PessoaRequest;
import com.example.gerenciadortarefas.pessoa.dto.PessoaResponse;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;
import com.example.gerenciadortarefas.pessoa.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public void salvar(PessoaRequest request) {
        repository.save(Pessoa.of(request.nome(), request.departamento()));
    }

    public Page<PessoaResponse> buscarTodos(PessoaFiltro filtro, PageRequest pageRequest) {
        return repository.findAll(filtro.toPredicate(), pageRequest)
                .map(pessoa -> PessoaResponse.of(pessoa, getTotalHorasGastas(pessoa)));
    }

    public PessoaResponse buscarPorId(Integer id) {
        var pessoa = findById(id);
        return PessoaResponse.of(pessoa, getTotalHorasGastas(pessoa));
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

    private String getTotalHorasGastas(Pessoa pessoa) {
        var duracoes = pessoa.getTarefas().stream()
                .map(tarefa -> Duration.ofHours(tarefa.getDuracao().getHour())
                        .plusMinutes(tarefa.getDuracao().getMinute())
                        .plusSeconds(tarefa.getDuracao().getSecond()))
                .reduce(Duration.ZERO, Duration::plus);
        return format("%02d:%02d:%02d", duracoes.toHours(), duracoes.toMinutesPart(),
                duracoes.toSecondsPart());
    }

    public PessoaGastoResponse buscarMediaHorasGastas(String nome, LocalDate dataInicial, LocalDate dataFinal) {
        var pessoa = repository.findByNomeAndPeriodo(nome, dataInicial, dataFinal)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada."));
        return new PessoaGastoResponse(pessoa.getNome(), getMediaHorasGastas(pessoa));
    }

    private String getMediaHorasGastas(Pessoa pessoa) {
        var qtdTarefas = pessoa.getTarefas().size();
        if (qtdTarefas == 0) {
            return format("%.2f", (double) qtdTarefas);
        }

        var totalHorasGastas = pessoa.getTarefas().stream()
                .map(tarefa -> tarefa.getDuracao().getHour())
                .mapToInt(Integer::intValue)
                .sum();

        var mediaHorasGastas = (double) totalHorasGastas / qtdTarefas;
        return format("%.2f", mediaHorasGastas);
    }
}
