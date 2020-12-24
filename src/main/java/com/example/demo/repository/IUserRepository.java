package com.example.demo.repository;

import com.example.demo.models.User;
import com.example.demo.repository.custom.IUserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> , IUserRepositoryCustom {
    Optional<User> findByUserFirstName( String userFirstName);
    Boolean existsByUserFirstName(String userFirstName);
    Boolean existsByUserEmail(String email);
}
