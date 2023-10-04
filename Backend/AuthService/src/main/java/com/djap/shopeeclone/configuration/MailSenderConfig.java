package com.djap.shopeeclone.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
public class MailSenderConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.protocol}")
    private String protocol;

    @Value("${spring.mail.properties.smtp.port}")
    private int port;

    @Value("${spring.mail.properties.smtp.auth}")
    private String auth;

    @Value("${spring.mail.properties.smtp.starttls.enable}")
    private String enable;

    @Value("${spring.mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", enable);
        props.put("mail.debug", debug);

        return mailSender;
    }

    @Bean
    public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // Resolver for HTML editable emails (which will be treated as a String)
        templateEngine.addTemplateResolver(thymeleafTemplateResolver());
        return templateEngine;
    }

    @Bean
    public ITemplateResolver thymeleafTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:templates/mail_template/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        templateResolver.setCacheable(false);

        return templateResolver;
    }


}
