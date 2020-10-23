package com.bootcampzup.mercadolivre.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "o nome deve ser preenchido")
    private String nome;
    @NotNull(message = "o valor deve ser preenchido")
    private double valor;
    @Positive(message = "a quantidade deve ser positiva")
    private int quantidade;
    @Valid
    @ManyToOne
    @NotNull
    private Users dono;
    @NotEmpty(message = "a descrição deve ser preenchida")
    @Length(max = 1000, message = "A descrição deve ter no maximo 1000 caracteres")
    private String descricao;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    private LocalDate instant;

    public Produto(@NotBlank(message = "o nome deve ser preenchido") String nome, @NotNull(message = "o valor deve ser preenchido") double valor, @Positive(message = "a quantidade deve ser positiva") int quantidade, @Valid @NotNull Users dono, @NotEmpty(message = "a descrição deve ser preenchida") @Length(max = 1000, message = "A descrição deve ter no maximo 1000 caracteres") String descricao, @NotNull Categoria categoria, LocalDate instant) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.dono = dono;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instant = instant;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Users getDono() {
        return dono;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getInstant() {
        return instant;
    }
}
