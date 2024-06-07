package com.example.userauth.userApp.service;

import com.example.userauth.userApp.entity.User;
import com.example.userauth.userApp.model.AppUserDetail;
import com.example.userauth.userApp.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG =
            "user with email $s not found";

    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder passwordEncoder;

//    bagaimana cara menemukan user
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user =   appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email))
                );
        return new AppUserDetail(user);
    }

    public String signUpUser(User user){
        //cek user yang register apa sudah ada
        boolean userExist = appUserRepository.findByEmail(user.getEmail()).isPresent();

        if(userExist){
            throw new IllegalStateException("user already taken");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        appUserRepository.save(user);

//        TODO: send confirmation token

        return "it works";

    }
}
