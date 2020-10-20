package com.bootcampzup.mercadolivre.controllers;

import com.bootcampzup.mercadolivre.models.Categoria;
import com.bootcampzup.mercadolivre.requests.CategoriaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categorias")
public class CreateCategoriaController {
    @PersistenceContext
    private EntityManager manager;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Categoria createCategoria (@Valid @RequestBody CategoriaRequest request) {
        Categoria categoria = request.toModel();
        if (request.getIdCategoriaMae() != null) {
            Categoria categoriaMae = this.manager.find(Categoria.class, request.getIdCategoriaMae());
            Assert.state(categoriaMae != null, "A categoria mãe com esse id não foi encontrada");
            categoria.setCategoriaMae(categoriaMae);
        }
        this.manager.persist(categoria);
        return categoria;
    }


}
