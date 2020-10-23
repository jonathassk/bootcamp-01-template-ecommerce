package com.bootcampzup.mercadolivre.controllers;

import com.bootcampzup.mercadolivre.config.UsuarioLogado;
import com.bootcampzup.mercadolivre.models.Categoria;
import com.bootcampzup.mercadolivre.models.Produto;
import com.bootcampzup.mercadolivre.models.Users;
import com.bootcampzup.mercadolivre.requests.ProdutoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/produtos")
public class createProdutoController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Produto createProduto (@Valid @RequestBody ProdutoRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
        Categoria categoria = manager.find(Categoria.class, request.getCategoriaId());
        Users usuario = usuarioLogado.get();
        Produto produto = request.toModel(categoria, usuario);
        manager.persist(produto);
        return produto;
    }
}
