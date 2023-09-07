package com.djap.shopeeclone.security;


import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.service.UserService;
import com.nimbusds.jwt.JWTClaimNames;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
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
                .claim("firstName", user.getFirstname())
                .claim("lastName", user.getLastname())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String extractName(String token){
        Jwt jwtClaimsSet = jwtDecoder.decode(token);
        return jwtClaimsSet.getClaim("firstName");
    }
    public String extractEmail(String token){
        Jwt jwtClaimsSet = jwtDecoder.decode(token);
        return jwtClaimsSet.getSubject();
    }

    public boolean validate(String token){
        if(token == null) {
            throw new RuntimeException("Token cannot be null");
        }

        try {
            Jwt jwt = jwtDecoder.decode(token);
            Assert.isTrue(new Date().before(Date.from(jwt.getExpiresAt())), "Token expired");
        }
        catch (Exception ex) {
            System.out.println("decode JWT exeption caused ");
            return false;
        }
        return true;
    }
}
