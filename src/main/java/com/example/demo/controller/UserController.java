package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository userRepo;

    @GetMapping("/get-all")
    public List<User> findAll(){
        return userRepo.findAll();
    }
}
