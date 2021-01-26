package com.example.demo.repository;

import com.example.demo.models.User;
import com.example.demo.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<User, Long> , UserRepositoryCustom {
    Optional<User> findByUserFirstName(String userFirstName);

    Boolean existsByUserFirstName(String userFirstName);

    Boolean existsByUserEmail(String email);

    Optional<User> findOneByUserFirstNameIgnoreCase(String userFirstName);
}
