package com.ibm.jwtdemo.jwtsecurity.controller;


import com.ibm.jwtdemo.jwtsecurity.model.JwtUser;
import com.ibm.jwtdemo.jwtsecurity.security.JwtGenertator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private JwtGenertator jwtGenertator;


    public TokenController(JwtGenertator jwtGenertator) {
        this.jwtGenertator = jwtGenertator;
    }

    @PostMapping
    public String generateToken(@RequestBody JwtUser jwtUser){
        
       return jwtGenertator.generate(jwtUser);
    }
}
