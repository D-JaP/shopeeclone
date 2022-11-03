package com.djap.shopeeclone.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthenticationService {
    Map<String, String> login(String email, String password);
}
