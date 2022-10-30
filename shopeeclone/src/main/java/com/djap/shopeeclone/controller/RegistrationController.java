package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.registration.RegistrationRequest;
import com.djap.shopeeclone.dto.registration.RegistrationResponse;
import com.djap.shopeeclone.mapper.auth.registration.RegistrationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final RegistrationMapper registrationMapper;

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.ok(new RegistrationResponse("Input field is not valid."));
        }
        return ResponseEntity.ok(registrationMapper.registerUser(request));
    }


}
