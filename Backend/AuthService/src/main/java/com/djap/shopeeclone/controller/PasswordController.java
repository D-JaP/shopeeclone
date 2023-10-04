package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.password.PasswordResetHandler;
import com.djap.shopeeclone.dto.password.PasswordResetMailRequest;
import com.djap.shopeeclone.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/password_reset")
public class PasswordController {
    private final PasswordResetService passwordResetService;

    @PostMapping()
    public ResponseEntity<String> resetPasswordRequest(@RequestBody PasswordResetMailRequest request) throws MessagingException {
        return ResponseEntity.ok(passwordResetService.sendPasswordResetEmail(request.getEmail()));
    }

    @PostMapping("/{token}")
    public ResponseEntity<String> resetPasswordHandler(@RequestBody PasswordResetHandler request) {
        return ResponseEntity.ok(passwordResetService.passwordResetHandler(request));
    }


}
