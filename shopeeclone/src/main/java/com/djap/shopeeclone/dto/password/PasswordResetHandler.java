package com.djap.shopeeclone.dto.password;

import lombok.Data;

@Data
public class PasswordResetHandler {
    private String token;
    private String password;
}
