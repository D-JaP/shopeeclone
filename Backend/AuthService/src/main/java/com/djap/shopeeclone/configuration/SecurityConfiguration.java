package com.djap.shopeeclone.configuration;

import com.djap.shopeeclone.model.CustomizeOauth2Users;
import com.djap.shopeeclone.service.CustomizeOauth2UserService;
import com.djap.shopeeclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    private final CustomizeOauth2UserService customizeOauth2UserService;
    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;

    @Value("${redirect-hostname}")
    private String rehostname;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**",
                        "/api/v1/registration/**",
                        "/api/v1/password_reset/**",
                        "/"
                ).permitAll()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.oauth2Login()
                .authorizationEndpoint()
                .baseUri("/api/v1/login/oauth2/authorization").and()
                .userInfoEndpoint()
                .userService(customizeOauth2UserService)
                .and()
                .successHandler((request, response, authentication) -> {
//                        update user details into db
                    CustomizeOauth2Users oauth2Users = (CustomizeOauth2Users) authentication.getPrincipal();
                    userService.handlePostOauthLogin(oauth2Users);
                })
                .defaultSuccessUrl(rehostname)
                .and()
                .exceptionHandling().accessDeniedPage("/403");

        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .deleteCookies("jwt-token")
                .deleteCookies("refresh-token")
                .logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserService userService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
