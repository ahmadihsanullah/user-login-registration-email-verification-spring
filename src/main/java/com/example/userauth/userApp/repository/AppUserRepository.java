package com.example.userauth.userApp.repository;

import com.example.userauth.userApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
