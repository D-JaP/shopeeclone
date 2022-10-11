package com.djap.shopeeclone.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.djap.shopeeclone.security.JwtProvider;
@Configuration
public class SecurityConfiguration {

    @Bean
    public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/auth/**",
                        "/api/v1/auth/login").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
