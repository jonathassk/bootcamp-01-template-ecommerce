package com.bootcampzup.mercadolivre.config;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {
    UserDetails map(Object shouldBeAUser);
}
