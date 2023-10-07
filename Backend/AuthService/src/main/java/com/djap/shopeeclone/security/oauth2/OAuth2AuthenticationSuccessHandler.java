package com.djap.shopeeclone.security.oauth2;


import com.djap.shopeeclone.model.CustomizeOauth2Users;
import com.djap.shopeeclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    @Value("${redirect-hostname}")
    private String redirectHostName;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
        CustomizeOauth2Users oauth2Users = (CustomizeOauth2Users) authentication.getPrincipal();
        userService.handlePostOauthLogin(oauth2Users);
        response.sendRedirect(redirectHostName);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomizeOauth2Users oauth2Users = (CustomizeOauth2Users) authentication.getPrincipal();
        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient  client = oAuth2AuthorizedClientService.loadAuthorizedClient(oauth2Token.getAuthorizedClientRegistrationId(), oauth2Token.getName());
        System.out.println(client.getAccessToken().getTokenValue());
        userService.handlePostOauthLogin(oauth2Users);
        response.sendRedirect(redirectHostName);
    }
}
