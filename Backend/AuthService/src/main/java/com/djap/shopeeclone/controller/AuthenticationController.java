package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.auth.AuthenticationRequest;
import com.djap.shopeeclone.dto.auth.AuthenticationResponse;

import com.djap.shopeeclone.dto.auth.RefreshTokenRequest;
import com.djap.shopeeclone.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Value("${hostname}")
    private String hostname;

    @Value("${jwt.jwt_token.expire_time_in_minutes}")
    private int expire_time_in_minutes ;

    @Value("${jwt.refresh_token.expire_time_in_days}")
    private int expire_time_in_days ;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        HashMap<String, String> response = authenticationService.login(request.getEmail(), request.getPassword());
        AuthenticationResponse body =  new AuthenticationResponse();
        body.setEmail(response.get("email"));
        body.setMessage(response.get("message"));
        HttpHeaders headers = new HttpHeaders();
//        Set cookie
        Cookie jwt_cookie = computeJwtCookie(response.get("jwt_token"));
        Cookie refresh_token = computeRefreshCookie(response.get("refresh_token"));
//        Add cookie to header
        headers.add("setJwtCookie", jwt_cookie.toString());
        headers.add("setRefreshCookie", refresh_token.toString());

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
    private Cookie computeJwtCookie(String token){
        Cookie jwt_cookie = new Cookie("jwt-token", token);
        jwt_cookie.setDomain(hostname);
        jwt_cookie.setPath("/");
        jwt_cookie.setHttpOnly(true);
        jwt_cookie.setMaxAge(expire_time_in_minutes *60);
        return jwt_cookie;
    }
    private Cookie computeRefreshCookie(String token){
        Cookie refresh_cookie = new Cookie("refresh-token", token);
        refresh_cookie.setDomain(hostname);
        refresh_cookie.setPath("/");
        refresh_cookie.setHttpOnly(true);
        refresh_cookie.setMaxAge(expire_time_in_days*24*60*60);
        return refresh_cookie;
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshTokenRequest request){
        HashMap<String, String> response = authenticationService.refresh(request.getToken());
        HttpHeaders header =  new HttpHeaders();
        Cookie jwt_token = computeJwtCookie(response.get("jwt-token"));
        Cookie refresh_token = computeRefreshCookie(response.get("refresh-token"));
        header.add("setJwtCookie", jwt_token.toString());
        header.add("setRefreshCookie", refresh_token.toString());
        AuthenticationResponse body = new AuthenticationResponse();
        body.setMessage(response.get("message"));
        return new ResponseEntity<>(body, header, HttpStatus.OK);
    }
}
