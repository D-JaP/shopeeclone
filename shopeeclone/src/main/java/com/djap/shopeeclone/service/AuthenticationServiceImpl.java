package com.djap.shopeeclone.service;

import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.repository.UserRepository;
import com.djap.shopeeclone.security.JwtProvider;
import com.djap.shopeeclone.exception.EmailPasswordNotMatchException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Override
    public Map<String, String> login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        AppUser user = userRepository.findByEmail(email).orElseThrow(
                () -> new EmailPasswordNotMatchException("Wrong username or password"));
//        if (!Objects.equals(user.getPassword(), password)) {
//            throw new EmailPasswordNotMatchException("Wrong username or password");
//        }
        String userRole = user.getUserRole().toString();
        HashMap<String, String> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("token", jwtProvider.GenerateToken(authentication));
        return response;
    }

}
