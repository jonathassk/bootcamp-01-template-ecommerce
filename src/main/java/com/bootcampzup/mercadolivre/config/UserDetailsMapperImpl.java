package com.bootcampzup.mercadolivre.config;

import com.bootcampzup.mercadolivre.models.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapperImpl implements UserDetailsMapper{

    @Override
    public UserDetails map(Object shouldBeAUser) {
        return new UsuarioLogado((Users) shouldBeAUser);
    }
}
