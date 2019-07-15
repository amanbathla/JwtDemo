package com.ibm.jwtdemo.jwtsecurity.security;

import com.ibm.jwtdemo.jwtsecurity.model.JwtAuthenticationToken;
import com.ibm.jwtdemo.jwtsecurity.model.JwtUser;
import com.ibm.jwtdemo.jwtsecurity.model.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JWtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator jwtValidator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

       JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
       String token = jwtAuthenticationToken.getToken();
       JwtUser jwtUser = jwtValidator.validate(token);

       if(jwtUser == null){
           throw new RuntimeException("Jwt Token is Invalid");
       }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.getRole());

      return new JwtUserDetails(jwtUser.getUserName(),jwtUser.getUserId(),token,grantedAuthorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return  (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
