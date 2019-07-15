package com.ibm.jwtdemo.jwtsecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {



    private String userName;
    private long userId;
    private String token;
    private Collection<? extends GrantedAuthority> grantedAuthorities;


    //Constructor
    public JwtUserDetails(String userName, long userId, String token, List<GrantedAuthority> grantedAuthorities) {

        this.userName = userName;
        this.userId = userId;
        this.token = token;
        this.grantedAuthorities = grantedAuthorities;

    }

    public String getUserName() {
        return userName;
    }

    public long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    //Getters

    //Override Methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
