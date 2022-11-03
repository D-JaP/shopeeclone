package com.djap.shopeeclone.service;


import com.djap.shopeeclone.dto.registration.MailTemplate;
import com.djap.shopeeclone.enums.UserRole;
import com.djap.shopeeclone.exception.EmailUsedException;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;

    @Value("${hostname}")
    private String hostname;

    public String registerUser(AppUser user) throws MessagingException {
//        Check email is exist
        userRepository.findByEmail(user.getEmail()).ifPresent(s -> {
            throw new EmailUsedException("Email is existed.");
        });

//        Persiting data and finish registration
        user.setIsActive(false);
        user.setUserRole(UserRole.USER);
        user.setActivationToken(UUID.randomUUID().toString());
//        password is auto bind

        userRepository.save(user);

//        send activation mail
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", user.getFirstName());
        attributes.put("registrationUrl", "http://" + hostname + "/api/v1/registration/activation/" + user.getActivationToken());
        mailSenderService.sendMessageHtml(user.getEmail(), MailTemplate.Subject.registration, MailTemplate.registration, attributes);

        return "User successfully registered.";
    }
}
