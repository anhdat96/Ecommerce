package com.example.demo.controller;

import com.example.demo.service.IUserService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping(value = "/create")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return iUserService.save(userDTO);
    }

    @GetMapping("/get-one-product/{id}")
    public UserDTO findOne(@PathVariable Long id) {
        return iUserService.findById(id).get();
    }

    @PutMapping("/update-user/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return iUserService.update(userDTO, id);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public void delete(@PathVariable Long id) {
        iUserService.delete(id);
    }


}
