package com.djap.shopeeclone.service;

import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<AppUser> getUsers(){
        return userRepository.findAll();
    }
}
