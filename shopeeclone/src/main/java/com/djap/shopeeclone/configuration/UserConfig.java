package com.djap.shopeeclone.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Value("")
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}