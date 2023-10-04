package com.djap.shopeeclone.dto.registration;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @Email(message = "Not valid email")
    @NotBlank(message = "Email should not be blank")
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Size(min = 6, max = 20, message = "Password length should be in range of 6 to 20 letters")
    private String password;

}
