package com.bootcampzup.mercadolivre.config;

import com.bootcampzup.mercadolivre.models.Users;
import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

public class UsuarioLogado implements UserDetails {
    private final Users usuario;
    private final User userDetails;

    public UsuarioLogado(@NotNull @Valid Users usuario) {
        this.usuario = usuario;
        userDetails = new User(usuario.getEmail(), usuario.getPassword(), List.of());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetails.isEnabled();
    }

    public Users get() {
        return usuario;
    }
}
