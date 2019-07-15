package com.ibm.jwtdemo.jwtsecurity.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private  String token;


    //Authentication Method
    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }


    //Getter and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //Override Methods


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
