package com.djap.shopeeclone.mapper.auth.registration;

import com.djap.shopeeclone.dto.registration.RegistrationRequest;
import com.djap.shopeeclone.dto.registration.RegistrationResponse;
import com.djap.shopeeclone.mapper.auth.CommonMapper;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {
    private final RegistrationService registrationService;
    private final CommonMapper commonMapper;

    public RegistrationResponse registerUser(RegistrationRequest request) throws MessagingException {
//        convert request to Appuser
        AppUser user = commonMapper.convertToEntity(request, AppUser.class);

        return new RegistrationResponse(registrationService.registerUser(user));
    }
}
