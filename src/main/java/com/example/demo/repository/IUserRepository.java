package com.example.demo.repository;

import com.example.demo.models.User;
import com.example.demo.repository.custom.IUserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User , Long> , IUserRepositoryCustom {
}
