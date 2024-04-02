package com.djap.shopeeclone.service;

import com.djap.shopeeclone.dto.password.PasswordResetHandler;
import com.djap.shopeeclone.dto.registration.MailTemplate;
import com.djap.shopeeclone.exception.email.EmailNotExistException;
import com.djap.shopeeclone.exception.token.PasswordResetTokenNotFoundException;
import com.djap.shopeeclone.exception.token.TokenExpiredException;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.model.PasswordResetToken;
import com.djap.shopeeclone.repository.PasswordResetTokenRepository;
import com.djap.shopeeclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;

    @Value("${domain}")
    private String hostname;

    public String sendPasswordResetEmail(String email) throws MessagingException {
//        Check if email exist
        AppUser user = userRepository.findByEmail(email).orElseThrow(
                EmailNotExistException::new);
//        Optional check captcha(TBD)


//        Create a new token for this email
        PasswordResetToken passwordResetToken = createNewPasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetTokenRepository.save(passwordResetToken);

//        Send email with password reset URL
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("passwordResetUrl", "http://" + hostname + "/api/v1/password_reset/" + passwordResetToken.getToken());
        mailSenderService.sendMessageHtml(user.getEmail(), MailTemplate.Subject.passwordReset, MailTemplate.passwordReset, attributes);

        return "Reset email sent.";
    }


    private PasswordResetToken createNewPasswordResetToken() {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(UUID.randomUUID().toString());
        passwordResetToken.setExpiryDate();
        return passwordResetToken;
    }

    //    Password reset request handler
    public String passwordResetHandler(PasswordResetHandler request) {
//        get and check unexpired token
        PasswordResetToken token = passwordResetTokenRepository.findByToken(request.getToken()).orElseThrow(
                PasswordResetTokenNotFoundException::new);
        if (!isTokenNonExpired(token)) {
            throw new TokenExpiredException();
        }
//        check password (TBD)

//        update password
        AppUser user = token.getUser();

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        passwordResetTokenRepository.delete(token);

        return "password successfully reset";
    }

    private Boolean isTokenNonExpired(PasswordResetToken token) {
        return token.getExpiryDate().isAfter(LocalDateTime.now());
    }
}
