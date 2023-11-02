package com.example.productservice.security;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
@Log4j2
public class JwtProvider {

    private final JwtDecoder jwtDecoder;


    public String extractName(String token) {
        Jwt jwtClaimsSet = jwtDecoder.decode(token);
        return jwtClaimsSet.getClaim("firstName");
    }

    public String extractEmail(String token) {
        Jwt jwtClaimsSet = jwtDecoder.decode(token);
        return jwtClaimsSet.getSubject();
    }

    public boolean validate(String token) {
        if (token == null) {
            throw new RuntimeException("Token cannot be null");
        }
        try {
            Jwt jwt = jwtDecoder.decode(token);
            Assert.isTrue(new Date().before(Date.from(jwt.getExpiresAt())), "Token expired");
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public boolean validateAccessToken(String token) {
        if (token == null) {
            throw new RuntimeException("Token cannot be null");
        }
        try {
            Jwt jwt = jwtDecoder.decode(token);
            Assert.isTrue(new Date().before(Date.from(jwt.getExpiresAt())), "Token expired");
            Assert.isTrue(jwt.getClaim("target").equals("access"), "jwt token is not an access token");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean validateJwt(Jwt jwt){
        if (jwt == null) {
            throw new RuntimeException("Token cannot be null");
        }
        try {
            Assert.isTrue(new Date().before(Date.from(jwt.getExpiresAt())), "Token expired");
            Assert.isTrue(jwt.getClaim("target").equals("access"), "jwt token is not an access token");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
        return true;
    }
}
