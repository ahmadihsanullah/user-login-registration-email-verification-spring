package com.example.userauth.registration;

import com.example.userauth.registration.token.ConfirmationToken;
import com.example.userauth.registration.token.ConfirmationTokenService;
import com.example.userauth.userApp.data.UserRole;
import com.example.userauth.userApp.entity.User;
import com.example.userauth.userApp.service.AppUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    private final EmailValidator emailValidator;

    private final ConfirmationTokenService confirmationTokenService;
    public String register(RegistrationRequest request) {
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
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(()->
                        new IllegalStateException("token not found"));

        if(confirmationToken.getConfirmedAt() != null){
            throw  new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if(expiresAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getUser().getEmail()
        );
        return "confirmed";
    }
}
