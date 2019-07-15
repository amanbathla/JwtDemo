package com.ibm.jwtdemo.jwtsecurity.config;

import com.ibm.jwtdemo.jwtsecurity.security.JWtAuthenticationProvider;
import com.ibm.jwtdemo.jwtsecurity.security.JwtAuthenticationEntryPoint;
import com.ibm.jwtdemo.jwtsecurity.security.JwtAuthenticationTokenFilter;
import com.ibm.jwtdemo.jwtsecurity.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWtAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;


    //jwt authentication Manager

    @Bean
    public AuthenticationManager authenticationManager(){

        return  new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    //Jwt Authentication filter
    //    1. set the custom autentication Manager
    //    2. Set the successfull Handler
    @Bean
    public JwtAuthenticationTokenFilter authenticationFilter(){

        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }


    // Custom method http security

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http.csrf().disable()
              .authorizeRequests().antMatchers("**/rest/").authenticated()
              .and()
              .exceptionHandling().authenticationEntryPoint(entryPoint)
               .and()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


      http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
      //add some default headers to the request
      http.headers().cacheControl();
    }
}
