package com.djap.shopeeclone.service;

import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.repository.UserRepository;
import com.djap.shopeeclone.security.JwtProvider;
import com.djap.shopeeclone.exception.EmailPasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
//    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Override
    public Map<String, String> login(String email, String password) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        AppUser user = userRepository.findByEmail(email).orElseThrow(
                () -> new EmailPasswordNotMatchException("Wrong username or password"));
        if (!Objects.equals(user.getPassword(), password)) {
            throw new EmailPasswordNotMatchException("Wrong username or password");
        }
        String userRole = user.getUserRole().toString();
        HashMap<String, String> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("token", jwtProvider.GenerateToken(email, userRole));

        return response;
    }

}
