package com.example.userauth.repository;

import com.example.userauth.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository {

    Optional<User> findByEmail(String email);
}
