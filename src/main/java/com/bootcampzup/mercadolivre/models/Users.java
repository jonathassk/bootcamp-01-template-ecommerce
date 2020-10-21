package com.bootcampzup.mercadolivre.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email(message = "email invalido")
    @NotBlank(message = "É necessário preencher o email")
    private String email;
    @NotBlank(message = "É necessário preencher a senha")
    @Length(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    @JsonIgnore
    private String password;
    private LocalDate momentoInscricao;

    public Users(String email, String password) {
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.momentoInscricao = LocalDate.now();
    }

    @Deprecated
    public Users() {
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getMomentoInscricao() {
        return momentoInscricao;
    }
}
