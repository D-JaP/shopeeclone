package com.djap.shopeeclone.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class MailSenderService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MailSenderService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
    @Value("${spring.mail.username}")
    private  String username;

    @Async
    public void sendMessageHtml(String to, String subject, String template,
                                Map<String, Object> attributes ) throws MessagingException {
//        Prepare context
        final Context ctx = new Context();
        ctx.setVariables(attributes);
//        Create HTML body using thymeleaf
        final String htmlBody = templateEngine.process(template, ctx);
//        Prepare message using helper
        MimeMessage messages  = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messages, true);
        helper.setFrom(username);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
//      Send mail
        mailSender.send(messages);
    }

}
