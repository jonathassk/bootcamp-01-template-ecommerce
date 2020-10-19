package com.bootcampzup.mercadolivre.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/v1/user")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
