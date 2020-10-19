package com.bootcampzup.mercadolivre.requests;

import com.bootcampzup.mercadolivre.annotations.UniqueValue;
import com.bootcampzup.mercadolivre.models.Users;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRequest {
    @Email(message = "email invalido")
    @NotBlank(message = "É necessário preencher o email")
    @UniqueValue(fieldname = "email", domainClass = Users.class, message = "email já cadastrado")
    private String email;
    @NotBlank(message = "É necessário preencher a senha")
    @Length(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users toModel () {
        return new Users(this.email, this.password);
    }
}
