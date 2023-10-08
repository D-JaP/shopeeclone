package com.djap.shopeeclone.configuration;

import com.djap.shopeeclone.enums.Provider;
import com.djap.shopeeclone.enums.UserRole;
import com.djap.shopeeclone.model.AppUser;

import com.djap.shopeeclone.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.djap.shopeeclone.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;


@Configuration
@EnableWebSecurity
@Log4j2
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
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
                        "/api/v1/**",
                        "/"
                ).permitAll()
                .antMatchers("/api/v1/user").authenticated()
                .and()
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.oauth2Login().loginPage("/login")
                .authorizationEndpoint()
                .baseUri("/api/v1/login/oauth2/authorization")
                .and()
                .userInfoEndpoint()
                .userService(oauth2LoginHandler())
                .oidcUserService(oidcLoginHandler())
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedPage("/403");

        http.logout()
                .logoutUrl("/api/v1/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .deleteCookies("jwtToken")
                .deleteCookies("refreshToken")
                .logoutSuccessUrl("/");

        return http.build();
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2LoginHandler() {
        return userRequest -> {
            Provider oauth2ClientName = Provider.valueOf(userRequest.getClientRegistration().getClientName().toUpperCase());

            OAuth2User user = new DefaultOAuth2UserService().loadUser(userRequest);
            AppUser appUser = new AppUser();

            appUser.setEmail(user.getAttribute("email"));
            appUser.setFirstname(user.getAttribute("given_name"));
            appUser.setLastname(user.getAttribute("family_name"));
            appUser.setPassword(passwordEncoder().encode(UUID.randomUUID().toString()));
            appUser.setIsActive(user.getAttribute("email_verified"));
            appUser.setOidcId(user.getName());
            appUser.setAuthorities(user.getAuthorities());
            appUser.setProvider(oauth2ClientName);
            appUser.setUserRole(UserRole.USER);
            appUser.setImgUrl(user.getAttribute("picture"));
            return appUser;
        };
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcLoginHandler() {
        return userRequest -> {
            Provider provider = Provider.valueOf(userRequest.getClientRegistration().getClientName().toUpperCase());
            OidcUserService delegate = new OidcUserService();
            OidcUser oidcUser = delegate.loadUser(userRequest);
//
            AppUser appUser = new AppUser();
            appUser.setEmail(oidcUser.getEmail());
            appUser.setFirstname(oidcUser.getGivenName());
            appUser.setLastname(oidcUser.getFamilyName());
            appUser.setPassword(passwordEncoder().encode(UUID.randomUUID().toString()));
            appUser.setIsActive(oidcUser.getEmailVerified());
            appUser.setOidcId(oidcUser.getName());
            appUser.setAuthorities(oidcUser.getAuthorities());
            appUser.setProvider(provider);
            appUser.setUserRole(UserRole.USER);
            appUser.setImgUrl(oidcUser.getPicture());

            return appUser;
        };
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
