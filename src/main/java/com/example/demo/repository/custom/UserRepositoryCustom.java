package com.example.demo.repository.custom;


import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {
    User search(String email);
}
