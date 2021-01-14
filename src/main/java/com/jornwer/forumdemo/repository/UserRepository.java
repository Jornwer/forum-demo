package com.jornwer.forumdemo.repository;

import com.jornwer.forumdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByLogin(String login);

    @Modifying
    @Transactional
    @Query("update users set login = ?1 where login = ?2")
    void updateUsername(String newUsername, String oldUsername);
}
