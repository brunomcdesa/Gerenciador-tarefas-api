package com.example.gerenciadortarefas.comum.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EDepartamento {

    VENDAS("Vendas"),
    CONSULTORIA("Consultoria"),
    RH("R.H"),
    TI("T.I"),
    SUPORTE("Suporte");

    private final String descricao;
}
