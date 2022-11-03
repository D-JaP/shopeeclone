package com.djap.shopeeclone.dto.registration;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties
public record MailTemplate() {

//    @Value("${spring.mail.template.registration}")
    public static String registration = "registration_template";

    public record Subject() {
        public static final String registration = "Activate Your ShopeeClone Account";

    }
}
