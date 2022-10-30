package com.djap.shopeeclone.mapper.auth;

import com.djap.shopeeclone.dto.registration.RegistrationRequest;
import com.djap.shopeeclone.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonMapper {

    private final ModelMapper mapper;
    public <T, S> S convertToEntity(T data, Class<S> type) {
        return mapper.map(data, type);
    }
}
