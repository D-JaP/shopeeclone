package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.auth.AuthenticationRequest;
import com.djap.shopeeclone.dto.auth.AuthenticationResponse;
import com.djap.shopeeclone.exception.token.RefreshTokenNotFoundException;
import com.djap.shopeeclone.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Value("${domain}")
    private String hostname;

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
        Cookie provider = computeCookie("login_provider", authResponse.get("login_provider"));

        response.addCookie(jwt_cookie);
        response.addCookie(refresh_token);
        response.addCookie(provider);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    private Cookie computeJwtCookie(String token) {
        Cookie jwt_cookie = new Cookie("jwtToken", token);

//        jwt_cookie.setDomain(hostname);
        jwt_cookie.setPath("/");
        jwt_cookie.setHttpOnly(false);
        jwt_cookie.setMaxAge(expire_time_in_minutes * 60);
        return jwt_cookie;
    }

    private Cookie computeRefreshCookie(String token) {
        Cookie refresh_cookie = new Cookie("refreshToken", token);
//        refresh_cookie.setDomain(hostname);
        refresh_cookie.setPath("/");
        refresh_cookie.setHttpOnly(true);
        refresh_cookie.setMaxAge(expire_time_in_days * 24 * 60 * 60);
        return refresh_cookie;
    }

    private Cookie computeCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
//        cookie.setDomain(hostname);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        cookie.setMaxAge(expire_time_in_days * 24 * 60 * 60);
        return cookie;
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(HttpServletRequest request,
                                                          HttpServletResponse response) {
        if (request.getCookies() == null) throw new RefreshTokenNotFoundException();

        Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> (c.getName().equals("refreshToken"))).findFirst().orElse(null);
        Cookie loginProvider = Arrays.stream(request.getCookies()).filter(c -> (c.getName().equals("login_provider"))).findFirst().orElse(null);

        if(cookie == null || loginProvider == null) throw new RefreshTokenNotFoundException();

        if(loginProvider.getValue().equals("local")){
            String token = cookie.getValue();

            HashMap<String, String> authResponse = authenticationService.refresh(token);

            Cookie jwt_token = computeJwtCookie(authResponse.get("jwt-token"));
            Cookie refresh_token = computeRefreshCookie(authResponse.get("refresh-token"));
            Cookie provider = computeCookie("login_provider", authResponse.get("login_provider"));

            response.addCookie(jwt_token);
            response.addCookie(refresh_token);
            response.addCookie(provider);

            AuthenticationResponse body = new AuthenticationResponse();
            body.setMessage(authResponse.get("message"));
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
        else if(loginProvider.getValue().equals("google")) {

        }
        return null;
    }

}
