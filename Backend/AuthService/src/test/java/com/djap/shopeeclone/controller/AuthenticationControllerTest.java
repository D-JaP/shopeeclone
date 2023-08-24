package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.auth.AuthenticationRequest;
import com.djap.shopeeclone.dto.auth.RefreshTokenRequest;
import com.djap.shopeeclone.dto.registration.RegistrationRequest;
import com.djap.shopeeclone.exception.token.RefreshTokenNotFoundException;
import com.djap.shopeeclone.model.RefreshToken;
import com.djap.shopeeclone.repository.RefreshTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.annotation.PostConstruct;
import java.util.Random;
import static com.djap.shopeeclone.util.AuthenticationTestConstants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository ;

    private final AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    private final RegistrationRequest registrationRequest = new RegistrationRequest();
    private final RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();

    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    void setUpLogin() {
        authenticationRequest.setEmail(USERNAME);
        authenticationRequest.setPassword(PASSWORD);
    }
    @Test
    void login() throws Exception {
        mockMvc.perform(    MockMvcRequestBuilders
                        .post(URL_AUTH_LOGIN)
                        .content(mapper.writeValueAsString(authenticationRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }
    @PostConstruct
    void setUpRegistration(){
        registrationRequest.setEmail("test" + new Random().nextInt(10000000) + "@test.com");
        registrationRequest.setFirstName(FIRSTNAME_REGISTER);
        registrationRequest.setLastName(LASTNAME_REGISTER);
        registrationRequest.setPassword(PASSWORD_REGISTER);
    }

    @Test
    void registration() throws  Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post(URL_AUTH_REGISTER)
                .content(mapper.writeValueAsString(registrationRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @PostConstruct
    void setUpRefreshToken(){
        RefreshToken refreshToken = refreshTokenRepository.findById(REFRESH_ACCOUNT_ID).orElseThrow(RefreshTokenNotFoundException::new);
        refreshTokenRequest.setToken(refreshToken.getToken());
    }
    @Test
    void refresh() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(URL_REFRESH_TOKEN)
                .content(mapper.writeValueAsString(refreshTokenRequest))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}