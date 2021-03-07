package com.project.poker.user_management.application.domain.repository;

import com.project.poker.user_management.application.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String userName);
}
