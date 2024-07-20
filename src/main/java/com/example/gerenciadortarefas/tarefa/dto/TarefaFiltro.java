package com.example.gerenciadortarefas.tarefa.dto;

import com.example.gerenciadortarefas.comum.enums.EBoolean;
import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record TarefaFiltro(
        Integer id,
        String titulo,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime prazoInicial,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime prazoFinal,
        EDepartamento departamento,
        LocalTime duracaoInicial,
        LocalTime duracaoFinal,
        EBoolean finalizado
) {

    public TarefaPredicate toPredicate() {
        return new TarefaPredicate()
                .comId(id)
                .comTitulo(titulo)
                .comDescricao(descricao)
                .comPrazo(prazoInicial, prazoFinal)
                .comDepartamento(departamento)
                .comDuracao(duracaoInicial, duracaoFinal)
                .comFinalizado(finalizado);
    }
}
