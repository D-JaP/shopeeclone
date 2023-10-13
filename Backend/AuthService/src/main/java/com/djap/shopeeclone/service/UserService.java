package com.djap.shopeeclone.service;

import com.djap.shopeeclone.enums.Provider;
import com.djap.shopeeclone.enums.UserRole;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    /* Handle post Oauth login, add user to database */
    public void handlePostOauthLogin(AppUser user) {
        AppUser matched_user = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (matched_user != null) {
            matched_user.setProvider(user.getProvider());
        }
        else{
            userRepository.save(user);
        }
    }

}
