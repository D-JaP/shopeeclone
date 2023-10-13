package com.djap.shopeeclone.security;


import com.djap.shopeeclone.model.AppUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
@Log4j2
public class JwtProvider {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String GenerateToken(AppUser user, int time, TemporalUnit unit, String target) {
        Instant now = Instant.now();

        /*Scope (TBD)*/
//        String scope = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("ShopeeCloneApp")
                .issuedAt(now)
                .expiresAt(now.plus(time, unit))
                .subject(user.getEmail())
                .claim("firstName", user.getFirstname())
                .claim("lastName", user.getLastname())
                .claim("target", target)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

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

    public boolean validateRefreshToken(String token) {
        if (token == null) {
            throw new RuntimeException("Token cannot be null");
        }
        try {
            Jwt jwt = jwtDecoder.decode(token);
            Assert.isTrue(new Date().before(Date.from(jwt.getExpiresAt())), "Token expired");
            Assert.isTrue(jwt.getClaim("target").equals("refresh"), "Jwt token provide is not refresh token");

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
        return true;
    }
}
