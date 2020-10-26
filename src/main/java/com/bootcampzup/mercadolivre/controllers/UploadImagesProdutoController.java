package com.bootcampzup.mercadolivre.controllers;

import com.bootcampzup.mercadolivre.config.UsuarioLogado;
import com.bootcampzup.mercadolivre.requests.ImageProdutoRequest;
import org.hibernate.mapping.Any;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UploadImagesProdutoController {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/produto/{id}/images")
    public void AdicionaImages (@Valid ImageProdutoRequest imagens, @AuthenticationPrincipal UsuarioLogado user, @PathVariable("id") Long produtoId) {

    }
}
