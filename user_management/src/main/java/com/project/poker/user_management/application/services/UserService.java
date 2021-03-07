package com.project.poker.user_management.application.services;

import com.project.poker.user_management.application.domain.model.User;
import com.project.poker.user_management.application.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUserByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
