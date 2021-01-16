package com.jornwer.forumdemo.service;

import com.jornwer.forumdemo.dto.UserDTO;
import com.jornwer.forumdemo.model.Role;
import com.jornwer.forumdemo.model.Status;
import com.jornwer.forumdemo.model.User;
import com.jornwer.forumdemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        log.info("User {} registered", user);
        return user;
    }

    @Transactional
    public void updateUsername(String oldUsername, String newUsername){
        userRepository.updateUsername(newUsername, oldUsername);
        log.info("User {} updated username to {}", oldUsername, newUsername);
    }

    public boolean isUserRegistered(String email){
        return userRepository.findUserByEmail(email).isPresent();
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findUserByLogin(username);
    }

}
