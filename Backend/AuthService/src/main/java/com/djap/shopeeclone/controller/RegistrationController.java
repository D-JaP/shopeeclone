package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.registration.RegistrationRequest;
import com.djap.shopeeclone.dto.registration.RegistrationResponse;
import com.djap.shopeeclone.mapper.auth.registration.RegistrationMapper;
import com.djap.shopeeclone.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/registration")
public class RegistrationController {

    private final RegistrationMapper registrationMapper;
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request, BindingResult bindingResult) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(new RegistrationResponse("Input field is not valid."));
        }
        return ResponseEntity.ok(registrationMapper.registerUser(request));
    }

    @GetMapping("/activation/{activation_token}")
    public ResponseEntity<String> activateAccountCode(@PathVariable String activation_token) {
        return ResponseEntity.ok(registrationService.activateUser(activation_token));
    }

    @GetMapping("/activation/resend/{exist_activation_token}")
    public ResponseEntity<String> resendActivationToken(@PathVariable String exist_activation_token) throws MessagingException {
        return ResponseEntity.ok(registrationService.resendActivationToken(exist_activation_token));
    }
}
