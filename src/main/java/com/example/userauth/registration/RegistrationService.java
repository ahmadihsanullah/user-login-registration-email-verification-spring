package com.example.userauth.registration;

import com.example.userauth.userApp.data.UserRole;
import com.example.userauth.userApp.entity.User;
import com.example.userauth.userApp.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    private final EmailValidator emailValidator;
    public String register(ReqistrationRequest request) {
//        1. buat eamil validator
        boolean isEmailValid = emailValidator.test(request.getEmail());
        if(!isEmailValid){
            throw new IllegalStateException("email is not valid");
        }

//        2. sign up
        return appUserService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword()git ,
                        UserRole.USER
                )
        );
    }
}
