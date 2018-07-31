package com.felipe.todo.controller;

import com.felipe.todo.security.JwtGenerator;
import com.felipe.todo.security.model.JwtUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/token")
public class TokenController {

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }


    @PostMapping
    public String generate(@RequestBody JwtUser jwtUser) {
        return jwtGenerator.generate(jwtUser);
    }
}

