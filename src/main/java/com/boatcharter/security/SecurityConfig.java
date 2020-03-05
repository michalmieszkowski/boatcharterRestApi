package com.boatcharter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserPrincipalDetailsService userPrincipalDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService, PasswordEncoder passwordEncoder) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/boats?category=", "/api/v1/boats/**","/api/v1/image/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .antMatchers("/api/v1/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/users").hasRole("MANAGEMENT")
                .antMatchers("/api/v1/reservation").hasRole("ADMIN")
                .antMatchers("/api/v1/reservation").hasRole("MANAGEMENT")
                .antMatchers(HttpMethod.POST, "/api/v1/reservation").hasAnyRole()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll();


    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }
}
