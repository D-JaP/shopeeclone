package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.auth.AuthenticationRequest;
import com.djap.shopeeclone.dto.auth.AuthenticationResponse;
import com.djap.shopeeclone.mapper.auth.AuthenticationMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationMapper authenticationMapper;

    @RequestMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationMapper.login(request));
    }


}
