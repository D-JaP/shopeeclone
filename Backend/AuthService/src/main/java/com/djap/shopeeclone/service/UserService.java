package com.djap.shopeeclone.service;

import com.djap.shopeeclone.enums.Provider;
import com.djap.shopeeclone.enums.UserRole;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.model.CustomUserDetails;
import com.djap.shopeeclone.model.CustomizeOauth2Users;
import com.djap.shopeeclone.repository.UserRepository;
import com.djap.shopeeclone.security.JwtProvider;
import com.djap.shopeeclone.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username){
        AppUser user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }

    /* Handle post Oauth login, add user to database */
    public void handlePostOauthLogin(CustomizeOauth2Users oauth2Users){
        Optional<AppUser> user = userRepository.findByEmail(oauth2Users.getEmail());
        Provider provider = Provider.valueOf(oauth2Users.getOauth2ClientName().toUpperCase());
        AppUser user_data;

        if (!user.isPresent()) {
            user_data = new AppUser();
            user_data.setEmail(oauth2Users.getEmail());
            user_data.setIsActive(true);
            user_data.setUserRole(UserRole.USER);
        }
        else {
            user_data = user.get();
        }
        user_data.setProvider(provider);
        userRepository.save(user_data);
    }

}
