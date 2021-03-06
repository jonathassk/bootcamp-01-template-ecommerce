package com.bootcampzup.mercadolivre.requests;

import com.bootcampzup.mercadolivre.annotations.ExistsId;
import com.bootcampzup.mercadolivre.models.Caracteristica;
import com.bootcampzup.mercadolivre.models.Categoria;
import com.bootcampzup.mercadolivre.models.Produto;
import com.bootcampzup.mercadolivre.models.Users;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class ProdutoRequest {
    @NotBlank(message = "o nome deve ser preenchido")
    private final String nome;
    @NotNull(message = "o valor deve ser preenchido")
    private final double valor;
    @Positive(message = "a quantidade deve ser positiva")
    private final int quantidade;
    @NotEmpty(message = "a descrição deve ser preenchida")
    @Length(max = 1000, message = "A descrição deve ter no maximo 1000 caracteres")
    private final String descricao;
    @ExistsId(fieldname = "id", domainClass = Categoria.class, message = "não foi encontrada essa categoria")
    private final long categoriaId;
    @Size(min = 3, message = "são necessarias pelo menos 3 caracteristicas")
    private List<CaracteristicasRequest> caracteristicas = new ArrayList<>();

    public ProdutoRequest(String nome, double valor, int quantidade, String descricao, long categoriaId, @Size(min = 3, message = "são necessarias pelo menos 3 caracteristicas") List<CaracteristicasRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas = caracteristicas;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public Produto toModel (Categoria categoria, Users usuario) {
        return new Produto(nome, valor, quantidade, usuario, descricao, categoria, LocalDate.now(), caracteristicas);
    }
}
