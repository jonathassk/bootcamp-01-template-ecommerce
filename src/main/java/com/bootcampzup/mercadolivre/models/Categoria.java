package com.bootcampzup.mercadolivre.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "A categoria deve ser preenchida")
    private String name;
    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(String name, Categoria categoriaMae) {
        this.name = name;
        this.categoriaMae = categoriaMae;
    }

    public Categoria(@NotEmpty(message = "A categoria deve ser preenchida") String name) {
        this.name = name;
    }

    public Categoria() {
    }

    public String getName() {
        return name;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
