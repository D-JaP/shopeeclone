package com.djap.shopeeclone.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class CustomizeOauth2Users implements OAuth2User {

    private OAuth2User oAuth2User;
    private String oauth2ClientName;

    public CustomizeOauth2Users(OAuth2User user, String oauth2ClientName) {
        this.oAuth2User = user;
        this.oauth2ClientName = oauth2ClientName;
    }

    @Override
    public <A> A getAttribute(String name) {
        return oAuth2User.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    public String getEmail(){
        return oAuth2User.<String>getAttribute("email");
    }

    public String getProvider(){
        return oAuth2User.<String>getAttribute("company");
    }


}
