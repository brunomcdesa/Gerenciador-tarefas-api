package com.example.gerenciadortarefas.modulos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRAZO")
    private LocalDateTime prazo;

    @Column(name = "DEPARTAMENTO")
    private String departamento;

    @Column(name = "DURACAO")
    private LocalTime duracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "FK_PESSOA", referencedColumnName = "id", insertable = false, updatable = false)})
    private Pessoa pessoaAlocada;

    private boolean finalizado;
}
