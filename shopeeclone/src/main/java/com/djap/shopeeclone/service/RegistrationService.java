package com.djap.shopeeclone.service;

import com.djap.shopeeclone.dto.registration.RegistrationRequest;
import com.djap.shopeeclone.enums.UserRole;
import com.djap.shopeeclone.exception.EmailUsedException;
import com.djap.shopeeclone.model.AppUser;
import com.djap.shopeeclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    public String registerUser(AppUser user) {
//        Check email is exist

        try{
            userRepository.findByEmail(user.getEmail()).ifPresent(s -> {
                throw new EmailUsedException("Email is existed.");
            });
        }
        catch (EmailUsedException e){
            System.out.print(e.getLocalizedMessage());
            return e.toString();
        }

//        Persiting data and finish registration
        user.setIsActive(false);
        user.setUserRole(UserRole.USER);
        userRepository.save(user);

        return "User successfully registered.";
    }
}
