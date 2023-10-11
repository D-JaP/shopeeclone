package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.userdata.UserDataResponse;
import com.djap.shopeeclone.model.AppUser;

import com.djap.shopeeclone.security.JwtProvider;
import com.djap.shopeeclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<UserDataResponse> getUserData(HttpServletRequest request) throws UsernameNotFoundException {
        String authHeader = request.getHeader("Authorization");
        String email, token;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            email = jwtProvider.extractEmail(token);
            AppUser user = userService.loadUserByUsername(email);
            UserDataResponse response  = new UserDataResponse(user);
            return ResponseEntity.ok(response);
        }
        else {
            throw new RuntimeException("Header does not have valid JWT token");
        }
    }
}
