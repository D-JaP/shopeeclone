package com.example.productservice.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
@Getter
@Setter
@ToString
public class RsaKeyProperties {
    private RSAPublicKey publicKey;
}

