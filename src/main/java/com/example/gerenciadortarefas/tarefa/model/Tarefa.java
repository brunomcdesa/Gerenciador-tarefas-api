package com.example.gerenciadortarefas.tarefa.model;

import com.example.gerenciadortarefas.comum.enums.EBoolean;
import com.example.gerenciadortarefas.comum.enums.EDepartamento;
import com.example.gerenciadortarefas.pessoa.model.Pessoa;
import com.example.gerenciadortarefas.tarefa.dto.TarefaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.example.gerenciadortarefas.comum.enums.EBoolean.F;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPARTAMENTO")
    private EDepartamento departamento;

    @Column(name = "DURACAO")
    private LocalTime duracao;

    @Column(name = "FINALIZADO")
    @Enumerated(EnumType.STRING)
    private EBoolean finalizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_PESSOA",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_PESSOA"))
    private Pessoa pessoaAlocada;

    public static Tarefa of(TarefaRequest request) {
        var tarefa = new Tarefa();
        BeanUtils.copyProperties(request, tarefa);
        tarefa.setFinalizado(F);
        return tarefa;
    }
}
