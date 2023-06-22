package com.djap.shopeeclone.security;


import com.djap.shopeeclone.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.TemporalUnit;


@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtEncoder jwtEncoder;

    public String GenerateToken(AppUser user, int time, TemporalUnit unit) {
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
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
