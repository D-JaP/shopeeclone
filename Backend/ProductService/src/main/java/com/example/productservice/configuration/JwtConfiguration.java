package com.example.productservice.configuration;

import com.example.productservice.security.RsaKeyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import java.security.interfaces.RSAPublicKey;
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

    public OAuth2TokenValidator<Jwt> tokenValidator() {
        final List<OAuth2TokenValidator<Jwt>> validators =
                List.of(new JwtTimestampValidator(),
                        new JwtIssuerValidator("ShopeeCloneApp"));
        return new DelegatingOAuth2TokenValidator<>(validators);
    }
}
