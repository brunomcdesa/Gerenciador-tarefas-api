package com.example.gerenciadortarefas.pessoa.model;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.tarefa.model.Tarefa;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DEPARTAMENTO")
    @Enumerated(EnumType.STRING)
    private EDepartamento departamento;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Tarefa> tarefas;

    public static Pessoa of(String nome, EDepartamento departamento) {
        return Pessoa.builder()
                .nome(nome)
                .departamento(departamento)
                .build();
    }
}
