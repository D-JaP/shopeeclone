package com.djap.shopeeclone.dto.userdata;

import com.djap.shopeeclone.model.AppUser;
import lombok.Data;

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
}
