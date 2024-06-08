package com.example.userauth.appuser.service;

import com.example.userauth.registration.token.ConfirmationToken;
import com.example.userauth.registration.token.ConfirmationTokenService;
import com.example.userauth.appuser.entity.AppUser;
import com.example.userauth.appuser.model.AppUserDetail;
import com.example.userauth.appuser.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG =
            "user with email $s not found";

    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ConfirmationTokenService confirmationTokenService;

//    bagaimana cara menemukan user
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        AppUser user =   appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email))
                );
        return new AppUserDetail(user);
    }

    public String signUpUser(AppUser user){
        //cek user yang register apa sudah ada
        boolean userExist = appUserRepository.findByEmail(user.getEmail()).isPresent();

        if(userExist){
            // TODO check of attributes are the same and
            // TODO if email not confirmed, send confirmation email again
            throw new IllegalStateException("user already taken");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        appUserRepository.save(user);

//        TODO: send confirmation token

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

//        TODO: send EMAIL notification
        return token;

    }

    public void enableAppUser(String email) {
        AppUser user = appUserRepository.findByEmail(email).get();
        user.setEnabled(true);
    }
}
