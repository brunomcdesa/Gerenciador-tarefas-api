package com.example.gerenciadortarefas.pessoa.model;

import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.pessoa.dto.PessoaRequest;
import com.example.gerenciadortarefas.tarefa.model.Tarefa;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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

    @OneToMany(mappedBy = "pessoaAlocada", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;

    public static Pessoa of(String nome, EDepartamento departamento) {
        return Pessoa.builder()
                .nome(nome)
                .departamento(departamento)
                .build();
    }

    public void atualizarDados(PessoaRequest request) {
        this.nome = isNotBlank(request.nome()) ? request.nome() : this.nome;
        this.departamento = request.departamento() != null ? request.departamento() : this.departamento;
    }
}
