package com.djap.shopeeclone.service;


import com.djap.shopeeclone.dto.registration.MailTemplate;
import com.djap.shopeeclone.enums.UserRole;
import com.djap.shopeeclone.exception.EmailUsedException;
import com.djap.shopeeclone.model.ActivationToken;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.repository.ActivationTokenRepository;
import com.djap.shopeeclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;
    private final ActivationTokenRepository activationTokenRepository;
    @Value("${hostname}")
    private String hostname;

    public String registerUser(AppUser user) throws MessagingException {
//        Check email is exist
        userRepository.findByEmail(user.getEmail()).ifPresent(s -> {
            throw new EmailUsedException(user.getEmail());
        });
//        Persiting data and finish registration
        user.setIsActive(false);
        user.setUserRole(UserRole.USER);

//        encode password
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        AppUser tmp_user = userRepository.save(user);

//        set new activation token
        ActivationToken token = setNewActivationToken(tmp_user);
//        send activation mail
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", user.getFirstName());
        attributes.put("registrationUrl", "http://" + hostname + "/api/v1/registration/activation/" + token.getToken());
        mailSenderService.sendMessageHtml(user.getEmail(), MailTemplate.Subject.registration, MailTemplate.registration, attributes);

        return "User successfully registered.";
    }

    public ActivationToken setNewActivationToken(AppUser user){
        ActivationToken activationToken = new ActivationToken();
        activationToken.setUser(user);
        activationToken.setToken(UUID.randomUUID().toString());

        activationToken.setExpiryDate();

        return activationTokenRepository.save(activationToken);
    }

    public String activateUser(String activation_token){
        ActivationToken token = getActivationTokenIfExist(activation_token);
        if (token !=null){
            if (isActivationTokenNonExpired(token)){
                AppUser user = token.getUser();
                user.setIsActive(true);
                userRepository.save(user);
                return "Successfully activate user.";
            }
            else return "Activation code expired.";
        }
        else return "Activation code not found.";
    }

    public String resendActivationToken(String exist_activation_token) throws MessagingException {
        ActivationToken activationToken = activationTokenRepository.findByToken(exist_activation_token).orElse(null);
        if (activationToken != null) {
            AppUser user = activationToken.getUser();
            activationToken.setToken(UUID.randomUUID().toString());
            activationToken.setExpiryDate();
            activationTokenRepository.save(activationToken);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("firstName", user.getFirstName());
            attributes.put("registrationUrl", "http://" + hostname + "/api/v1/registration/activation/" + activationToken.getToken());
            mailSenderService.sendMessageHtml(user.getEmail(), MailTemplate.Subject.registration, MailTemplate.registration, attributes);
            return "Activation token resend successfully";
        }
        else return "Existing Activation token not found";
    }

    private Boolean isActivationTokenNonExpired(ActivationToken activationToken){
        return activationToken.getExpiryDate().isAfter(LocalDateTime.now());
    }

    private ActivationToken getActivationTokenIfExist(String activationToken){
        Optional<ActivationToken> token = activationTokenRepository.findByToken(activationToken);
        if (token.isPresent()){
            return token.get();
        }
        else return null;
    }
}
