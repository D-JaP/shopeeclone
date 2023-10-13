package com.djap.shopeeclone.configuration;


import com.djap.shopeeclone.dto.auth.ErrorResponse;
import com.djap.shopeeclone.exception.token.AuthenticationFailException;
import com.djap.shopeeclone.security.JwtProvider;
import com.djap.shopeeclone.service.Oauth2UserService;
import com.djap.shopeeclone.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final Oauth2UserService oAuth2UserService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null, email = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            if (token.startsWith("ya29")) // Google access token
            {   // handle oauth2 access token
//                CommonOAuth2Provider
                ClientRegistration google = clientRegistrationRepository.findByRegistrationId("google");
                OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, token, null, null);
                OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(google, oAuth2AccessToken);
                OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
                OAuth2AuthenticationToken oauth2Token = new OAuth2AuthenticationToken(oAuth2User,oAuth2User.getAuthorities(), google.getRegistrationId());
                SecurityContextHolder.getContext().setAuthentication(oauth2Token);
            }
            else {  // normal access token
                email = defaultJWTTokenHandler(token, response);
            }

        }

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(email);
            if (jwtProvider.validateAccessToken(token)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }
    private String defaultJWTTokenHandler (String token, HttpServletResponse response) throws IOException {
        try { // try to decode and get email
            String email = jwtProvider.extractEmail(token);
            return email;
        }
        catch (JwtValidationException ex){
            ErrorResponse errorResponse = new ErrorResponse(ex);
            response.setStatus(403);
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return null;
        }

    }


}
