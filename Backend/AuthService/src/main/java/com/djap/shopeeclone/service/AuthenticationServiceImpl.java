package com.djap.shopeeclone.service;

import com.djap.shopeeclone.exception.EmailPasswordNotMatchException;
import com.djap.shopeeclone.exception.token.RefreshTokenNotFoundException;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.model.RefreshToken;
import com.djap.shopeeclone.repository.RefreshTokenRepository;
import com.djap.shopeeclone.repository.UserRepository;
import com.djap.shopeeclone.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;


@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.jwt_token.expire_time_in_minutes}")
    private int jwtTokenExpiredMinutes;
    @Value("${jwt.refresh_token.expire_time_in_days}")
    private int refreshTokenExpiredDays;

    @Override
    public HashMap<String, String> login(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception ex) {
            throw new EmailPasswordNotMatchException();
        }

        AppUser user = userRepository.findByEmail(email).orElseThrow(
                () -> new EmailPasswordNotMatchException());
//        if (!Objects.equals(user.getPassword(), password)) {
//            throw new EmailPasswordNotMatchException("Wrong username or password" );
//        }
        HashMap<String, String> response = new HashMap<>();
        String jwt_token = jwtProvider.GenerateToken(user, jwtTokenExpiredMinutes, ChronoUnit.MINUTES, "access");
        String refresh_token = jwtProvider.GenerateToken(user, refreshTokenExpiredDays, ChronoUnit.DAYS , "refresh");
        response.put("email", user.getEmail());
        response.put("jwt_token", jwt_token);
        response.put("refresh_token", refresh_token);
        response.put("login_provider", "local");

        RefreshToken refreshToken = refreshTokenRepository.findById(user.getId()).orElse(new RefreshToken());
//        TBD: save refresh_token to database
        if (refreshToken.getUser() == null) {
            refreshToken.setUser(user);
        }
        refreshToken.setToken(refresh_token);
        refreshToken.setExpirationDate();
        refreshTokenRepository.save(refreshToken);

        return response;
    }


    @Override
    public HashMap<String, String> refresh(String token) {
//        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(
//                RefreshTokenNotFoundException::new
//        );
        HashMap<String, String> response = new HashMap<>();
//        Check if refresh token has not expired
        if (jwtProvider.validateRefreshToken(token)) {
            log.info("validate refresh token succeed");
            Jwt jwt = jwtProvider.getJwtDecoder().decode(token);
            AppUser user = userRepository.findByEmail(jwt.getSubject()).orElseThrow(RefreshTokenNotFoundException::new);
            /* remake the jwt token and refresh token */
            String refresh_token = jwtProvider.GenerateToken(user, refreshTokenExpiredDays, ChronoUnit.DAYS, "refresh");
            String jwt_token = jwtProvider.GenerateToken(user, jwtTokenExpiredMinutes, ChronoUnit.MINUTES, "access");

            RefreshToken newRefreshToken = refreshTokenRepository.findById(user.getId()).orElse(new RefreshToken());
            if (newRefreshToken.getUser() == null) {
                newRefreshToken.setId(user.getId());
            }
            newRefreshToken.setToken(refresh_token);
            newRefreshToken.setExpirationDate();
            refreshTokenRepository.save(newRefreshToken);
            response.put("jwt-token", jwt_token);
            response.put("refresh-token", refresh_token);
            response.put("login_provider", "local");
            response.put("message", "Refresh token request success.");
        }
        else { // try oauth2 refresh token
//            DefaultRefreshTokenTokenResponseClient
//            OAuth2RefreshTokenGrantRequest
        }



        return response;
    }

    @Override
    public String authenticate(String access_token) {
        if (access_token == null) {
            throw new RuntimeException("Token is not valid");
        }
        if(jwtProvider.validateAccessToken(access_token)){
            return "Token is validated.";
        }
        else {
            throw new RuntimeException("Token is not valid");
        }
    }


    private boolean isRefreshTokenExpired(RefreshToken refreshToken) {
        return !refreshToken.getExpirationDate().isAfter(LocalDateTime.now());
    }




}
