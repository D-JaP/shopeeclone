package com.djap.shopeeclone.dto.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "redirect")
@Getter
@Setter
public class RedirectHost {
    private String redirectHost;
}
