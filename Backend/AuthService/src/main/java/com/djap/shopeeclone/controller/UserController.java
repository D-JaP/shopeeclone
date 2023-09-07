package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.service.AuthenticationService;
import com.djap.shopeeclone.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> getNameFromToken(@CookieValue("jwtToken") String jwtToken) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value =  objectMapper.writeValueAsString(userService.getNameFromToken(jwtToken));
        return ResponseEntity.ok(value);
    }
}
