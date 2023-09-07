package com.djap.shopeeclone.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface AuthenticationService {

    /* Login using JWT */
    HashMap<String, String> login(String email, String password);
    /* Refresh token handle */
    HashMap<String, String> refresh(String refresh_token);

}
