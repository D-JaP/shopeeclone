package com.djap.shopeeclone.controller;

import com.djap.shopeeclone.dto.userdata.UserDataResponse;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.security.JwtProvider;
import com.djap.shopeeclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/auth/user")
public class UserController {
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('USER', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<UserDataResponse> getUserData(HttpServletRequest request, Authentication authentication) throws UsernameNotFoundException {
        UserDataResponse response= null;
        if (authentication.getPrincipal() instanceof DefaultOAuth2User){
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            response  = new UserDataResponse(user);
        }
        else{
            AppUser user = (AppUser) authentication.getPrincipal();
            response = new UserDataResponse(user);
        }

        return ResponseEntity.ok(response);

    }
}
