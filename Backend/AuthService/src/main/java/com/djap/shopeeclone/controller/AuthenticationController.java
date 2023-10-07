package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.auth.AuthenticationRequest;
import com.djap.shopeeclone.dto.auth.AuthenticationResponse;
import com.djap.shopeeclone.exception.token.RefreshTokenNotFoundException;
import com.djap.shopeeclone.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    //    @Value("${hostname}")
    private String hostname = "localhost";

    @Value("${jwt.jwt_token.expire_time_in_minutes}")
    private int expire_time_in_minutes;

    @Value("${jwt.refresh_token.expire_time_in_days}")
    private int expire_time_in_days;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        HashMap<String, String> authResponse = authenticationService.login(request.getEmail(), request.getPassword());
        AuthenticationResponse body = new AuthenticationResponse();
        body.setEmail(authResponse.get("email"));
        body.setMessage(authResponse.get("message"));
//        Set cookie
        Cookie jwt_cookie = computeJwtCookie(authResponse.get("jwt_token"));
        Cookie refresh_token = computeRefreshCookie(authResponse.get("refresh_token"));

        response.addCookie(jwt_cookie);
        response.addCookie(refresh_token);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    private Cookie computeJwtCookie(String token) {
        Cookie jwt_cookie = new Cookie("jwtToken", token);

        jwt_cookie.setDomain(hostname);
        jwt_cookie.setPath("/");
        jwt_cookie.setHttpOnly(false);
        jwt_cookie.setMaxAge(expire_time_in_minutes * 60);
        return jwt_cookie;
    }

    private Cookie computeRefreshCookie(String token) {
        Cookie refresh_cookie = new Cookie("refreshToken", token);
        refresh_cookie.setDomain(hostname);
        refresh_cookie.setPath("/");
        refresh_cookie.setHttpOnly(true);
        refresh_cookie.setMaxAge(expire_time_in_days * 24 * 60 * 60);
        return refresh_cookie;
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(HttpServletRequest request,
                                                          HttpServletResponse response) {
        if (request.getCookies() == null) throw new RefreshTokenNotFoundException();

        String token = Arrays.stream(request.getCookies()).filter(cookie -> (cookie.getName().equals("refreshToken"))).findFirst().get().getValue();

        if(token == null ) throw new RefreshTokenNotFoundException();

        HashMap<String, String> authResponse = authenticationService.refresh(token);

        Cookie jwt_token = computeJwtCookie(authResponse.get("jwt-token"));
        Cookie refresh_token = computeRefreshCookie(authResponse.get("refresh-token"));

        response.addCookie(jwt_token);
        response.addCookie(refresh_token);

        AuthenticationResponse body = new AuthenticationResponse();
        body.setMessage(authResponse.get("message"));
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
