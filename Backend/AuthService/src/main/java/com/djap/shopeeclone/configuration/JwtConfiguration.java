package com.djap.shopeeclone.configuration;

import com.djap.shopeeclone.security.RsaKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JwtConfiguration {

    private final RsaKeyProperties rsaKeys;

    @Bean
    JwtDecoder jwtDecoder() {
        final NimbusJwtDecoder decoder = NimbusJwtDecoder.withPublicKey(rsaKeys.getPublicKey()).build();
        decoder.setJwtValidator(tokenValidator());
        return decoder;
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.getPublicKey()).privateKey(rsaKeys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    public OAuth2TokenValidator<Jwt> tokenValidator() {
        final List<OAuth2TokenValidator<Jwt>> validators =
                List.of(new JwtTimestampValidator(),
                        new JwtIssuerValidator("ShopeeCloneApp"));
        return new DelegatingOAuth2TokenValidator<>(validators);
    }
}
