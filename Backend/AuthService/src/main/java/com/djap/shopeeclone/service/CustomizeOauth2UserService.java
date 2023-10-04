package com.djap.shopeeclone.service;

import com.djap.shopeeclone.model.CustomizeOauth2Users;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomizeOauth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String oauth2ClientName = userRequest.getClientRegistration().getClientName();
        OAuth2User user = super.loadUser(userRequest);
        return new CustomizeOauth2Users(user, oauth2ClientName);
    }
}
