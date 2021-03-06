package com.bootcampzup.mercadolivre.config;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationManager authManager;
    private final TokenManager tokenManager;

    @Autowired
    public AuthenticationController(AuthenticationManager authManager, TokenManager tokenManager) {
        this.authManager = authManager;
        this.tokenManager = tokenManager;
    }

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody LoginInputDto loginInfo) {
        UsernamePasswordAuthenticationToken authenticationToken = loginInfo.build();
        try {
            Authentication authentication = authManager.authenticate(authenticationToken);
            String jwt = tokenManager.gerenateToken(authentication);
            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException e) {
            log.error("[Autenticacao] {}", e);
            return ResponseEntity.status(401).body("email: "+loginInfo.getEmail());
        }
    }
}