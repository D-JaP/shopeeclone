package com.djap.shopeeclone.dto.userdata;

import com.djap.shopeeclone.model.AppUser;
import lombok.Data;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Data
public class UserDataResponse {

    private String email;
    private String firstname;
    private String lastname;

    public UserDataResponse(AppUser appUser){
        this.email = appUser.getEmail();
        this.firstname = appUser.getFirstname();
        this.lastname = appUser.getLastname();
    };

    public UserDataResponse(OAuth2User oAuth2User){
        this.email = oAuth2User.getAttribute("email");
        this.firstname = oAuth2User.getAttribute("given_name");
        this.lastname  = oAuth2User.getAttribute("family_name");
    }
}
