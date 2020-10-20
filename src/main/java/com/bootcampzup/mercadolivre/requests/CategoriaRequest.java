package com.bootcampzup.mercadolivre.requests;

import com.bootcampzup.mercadolivre.annotations.UniqueValue;
import com.bootcampzup.mercadolivre.models.Categoria;

import javax.validation.constraints.NotEmpty;

public class CategoriaRequest {
    @NotEmpty(message = "A categoria deve ser preenchida")
    @UniqueValue(message = "Essa categoria já existe", domainClass = Categoria.class, fieldname = "name")
    private String name;
    private Long idCategoriaMae;

    public CategoriaRequest(String name, Long idCategoriaMae) {
        this.name = name;
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getName() {
        return name;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public Categoria toModel () {
        return new Categoria(this.name);
    }
}
