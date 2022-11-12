package com.djap.shopeeclone;

import com.djap.shopeeclone.dto.registration.MailTemplate;
import com.djap.shopeeclone.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableConfigurationProperties({RsaKeyProperties.class, MailTemplate.class})
@SpringBootApplication
@EnableAsync
public class ShopeecloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeecloneApplication.class, args);
    }
}
