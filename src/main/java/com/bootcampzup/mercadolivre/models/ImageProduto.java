package com.bootcampzup.mercadolivre.models;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImageProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @NotNull(message = "o produto deve ser existente")
    @Valid
    private Produto produto;
    @URL
    @NotNull(message = "a url deve ser preenchida")
    private String url;

    public ImageProduto(@NotNull @Valid Produto produto, @URL @NotNull String url) {
        this.produto = produto;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageProduto that = (ImageProduto) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
