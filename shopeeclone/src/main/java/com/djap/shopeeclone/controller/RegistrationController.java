package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.registration.RegistrationRequest;
import com.djap.shopeeclone.dto.registration.RegistrationResponse;
import com.djap.shopeeclone.mapper.auth.registration.RegistrationMapper;
import com.djap.shopeeclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final RegistrationMapper registrationMapper;
    private final UserService userService;
    @PostMapping
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request, BindingResult bindingResult) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(new RegistrationResponse("Input field is not valid."));
        }
        return ResponseEntity.ok(registrationMapper.registerUser(request));
    }

    @GetMapping("/activation/{activation_token}")
    public ResponseEntity<String> activateAccountCode(@PathVariable String activation_token){
        if (userService.activateUser(activation_token)){
            return ResponseEntity.ok("User successfully activated.");
        }
        return ResponseEntity.ok("Activation code not found.");


    }
}
