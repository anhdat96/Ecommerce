package com.example.demo.controller;

import com.example.demo.service.IUserService;
import com.example.demo.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private IUserService userService;

    @PostMapping(value = "/create")
    @Transactional
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping(value = "/get-all")
    public List<UserDTO> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return userService.findAll(page, size);
    }

    @GetMapping(value = "/get-one")
    public UserDTO findById(@RequestParam Long id) {
        return userService.findById(id);
    }

    @PutMapping(value = "/update")
    @Transactional
    public UserDTO update(@RequestParam Long id, @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        userService.deleteById(id);
    }
}