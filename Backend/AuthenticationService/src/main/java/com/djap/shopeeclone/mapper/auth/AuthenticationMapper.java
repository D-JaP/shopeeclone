package com.djap.shopeeclone.mapper.auth;

import com.djap.shopeeclone.dto.auth.AuthenticationRequest;
import com.djap.shopeeclone.dto.auth.AuthenticationResponse;
import com.djap.shopeeclone.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {
    private final AuthenticationService authenticationService;

    public AuthenticationResponse login(AuthenticationRequest request) {

        Map<String, String> credentials = authenticationService.login(request.getEmail(), request.getPassword());
        AuthenticationResponse response = new AuthenticationResponse();
        response.setEmail(credentials.get("email"));
        response.setToken(credentials.get("token"));
        return response;
    }
}
