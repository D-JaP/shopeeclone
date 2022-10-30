package com.djap.shopeeclone.mapper.auth.registration;

import com.djap.shopeeclone.dto.registration.RegistrationRequest;
import com.djap.shopeeclone.dto.registration.RegistrationResponse;
import com.djap.shopeeclone.mapper.auth.CommonMapper;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {
    private final RegistrationService registrationService;
    private final CommonMapper commonMapper;
    public RegistrationResponse registerUser(RegistrationRequest request){
//        convert request to Appuser
        AppUser user = commonMapper.convertToEntity(request, AppUser.class);
        RegistrationResponse response = new RegistrationResponse(registrationService.registerUser(user));

        return response;
    }

}
