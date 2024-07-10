package com.example.gerenciadortarefas.tarefa.dto;

import com.example.gerenciadortarefas.comum.annotations.DateTimePattern;
import com.example.gerenciadortarefas.comum.enums.EBoolean;
import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.tarefa.model.Tarefa;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record TarefaResponse(
        String titulo,
        String descricao,
        EDepartamento departamento,
        @DateTimePattern
        LocalDateTime prazo,
        LocalTime duracao,
        EBoolean finalizado
) {

        public static TarefaResponse of(Tarefa tarefa) {
                return new TarefaResponse(
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getDepartamento(),
                        tarefa.getPrazo(),
                        tarefa.getDuracao(),
                        tarefa.getFinalizado()
                );
        }
}
