package com.djap.shopeeclone.mapper.auth;

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
