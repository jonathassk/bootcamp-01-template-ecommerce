package com.bootcampzup.mercadolivre.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenManager tokenManager;
    private final UsersService usersService;
    public JwtAuthenticationFilter(TokenManager tokenManager, UsersService usersService) {
        this.tokenManager = tokenManager;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenOptional = getTokenFromRequest(httpServletRequest);
        if (tokenOptional.isPresent() && tokenManager.isValid(tokenOptional.get())) {
            String username = tokenManager.getUserName(tokenOptional.get());
            UserDetails userDetails = usersService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        return Optional.ofNullable(token);
    }
}
