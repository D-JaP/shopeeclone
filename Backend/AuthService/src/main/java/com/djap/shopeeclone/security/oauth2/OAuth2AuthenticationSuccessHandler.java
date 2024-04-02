package com.djap.shopeeclone.security.oauth2;


import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Value("${frontendurl}")
    private String frontendURL;
    @Value("${domain}")
    private String domain;

    private final UserService userService;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
        AppUser oauth2Users = (AppUser) authentication.getPrincipal();
        userService.handlePostOauthLogin(oauth2Users);
//        String baseURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        System.out.println(frontendURL);
        response.sendRedirect(frontendURL);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        AppUser oauth2Users = (AppUser) authentication.getPrincipal();
        userService.handlePostOauthLogin(oauth2Users);
//        Token handler
        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient  client = oAuth2AuthorizedClientService.loadAuthorizedClient(oauth2Token.getAuthorizedClientRegistrationId(), oauth2Token.getName());

        if (client.getAccessToken()!= null){
            Cookie accessCookie = computeCookieFromToken("jwtToken", client.getAccessToken(), false);
            response.addCookie(accessCookie);
        }
//        Access type need to be set to offline to get refresh token
        if(client.getRefreshToken()!= null){
            Cookie refreshCookie = computeCookieFromToken("refreshToken", client.getRefreshToken(), true);
            response.addCookie(refreshCookie);
        }
//        redirect to frontend endpoint
//        String baseURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");

        response.sendRedirect(frontendURL);
    }

    private Cookie computeCookieFromToken(String cookie_name, AbstractOAuth2Token token, Boolean httponly){
        Cookie cookie = new Cookie(cookie_name, token.getTokenValue());
//        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(httponly);
        if (token.getExpiresAt()!= null && token.getIssuedAt()!=null){
            cookie.setMaxAge((int) (token.getExpiresAt().getEpochSecond()-token.getIssuedAt().getEpochSecond()));
        }
        return cookie;
    }

}
