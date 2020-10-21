package com.bootcampzup.mercadolivre.controllers;

import com.bootcampzup.mercadolivre.models.Users;
import com.bootcampzup.mercadolivre.requests.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class CreateUserController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Users> createUser (@Valid @RequestBody UserRequest req, UriComponentsBuilder uriComponentsBuilder) {
        Users user = req.toModel();
        manager.persist(user);
        return ResponseEntity.created(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri()).body(user);
    }
}
