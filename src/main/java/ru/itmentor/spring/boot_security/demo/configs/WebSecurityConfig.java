package ru.itmentor.spring.boot_security.demo.configs;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.itmentor.spring.boot_security.demo.security.AuthProvidedImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvidedImpl authProvided;

    @Autowired
    public WebSecurityConfig(AuthProvidedImpl authProvided) {
        this.authProvided = authProvided;
    }

    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvided);

    }
}