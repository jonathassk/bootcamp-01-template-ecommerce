package com.bootcampzup.mercadolivre.requests;

import com.bootcampzup.mercadolivre.models.Caracteristica;
import com.bootcampzup.mercadolivre.models.Categoria;
import com.bootcampzup.mercadolivre.models.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicasRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicasRequest (String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica toModel (@Valid @NotNull Produto produto) {
        return new Caracteristica(nome, descricao, produto);
    }
}
