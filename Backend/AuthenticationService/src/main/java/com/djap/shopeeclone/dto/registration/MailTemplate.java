package com.djap.shopeeclone.dto.registration;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties

public class MailTemplate {

//    @Value("${spring.mail.template.registration}")
    public static final String registration = "registration_template";
    public static final String passwordReset = "password_reset_template";
    public class Subject {
        public static final String registration = "Activate Your ShopeeClone Account";
        public static final String passwordReset = "ShopeeClone Password Reset";
    }
}
