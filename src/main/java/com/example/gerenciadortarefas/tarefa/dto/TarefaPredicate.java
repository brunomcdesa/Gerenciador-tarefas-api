package com.example.gerenciadortarefas.tarefa.dto;

import com.example.gerenciadortarefas.comum.enums.EBoolean;
import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.configuracoes.PredicateBase;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.example.gerenciadortarefas.tarefa.model.QTarefa.tarefa;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class TarefaPredicate extends PredicateBase {

    public TarefaPredicate comId(Integer id) {
        if (id != null) {
            builder.and(tarefa.id.eq(id));
        }
        return this;
    }

    public TarefaPredicate comTitulo(String titulo) {
        if (isNotBlank(titulo)) {
            builder.and(tarefa.titulo.containsIgnoreCase(titulo));
        }
        return this;
    }

    public TarefaPredicate comDescricao(String descricao) {
        if (isNotBlank(descricao)) {
            builder.and(tarefa.descricao.containsIgnoreCase(descricao));
        }
        return this;
    }

    public TarefaPredicate comPrazo(LocalDateTime prazoInicial, LocalDateTime prazoFinal) {
        if (prazoInicial != null && prazoFinal != null) {
            builder.and(tarefa.prazo.between(prazoInicial, prazoFinal));
        }
        return this;
    }

    public TarefaPredicate comDepartamento(EDepartamento departamento) {
        if (departamento != null) {
            builder.and(tarefa.departamento.eq(departamento));
        }
        return this;
    }

    public TarefaPredicate comDuracao(LocalTime duracaoInicial, LocalTime duracaoFinal) {
        if (duracaoInicial != null && duracaoFinal != null) {
            builder.and(tarefa.duracao.between(duracaoInicial, duracaoFinal));
        }
        return this;
    }

    public TarefaPredicate comFinalizado(EBoolean finalizado) {
        if (finalizado != null) {
            builder.and(tarefa.finalizado.eq(finalizado));
        }
        return this;
    }
}
