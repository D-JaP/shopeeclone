package com.djap.shopeeclone.service;

import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.repository.UserRepository;
import com.djap.shopeeclone.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService{

    private final UserRepository userRepository;
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<AppUser> getUsers(){
        return userRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return UserPrincipal.create(user);
    }

}
