package com.djap.shopeeclone;

import com.djap.shopeeclone.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ShopeecloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopeecloneApplication.class, args);
	}
}
