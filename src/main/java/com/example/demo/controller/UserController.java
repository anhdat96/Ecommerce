package com.example.demo.controller;

import com.example.demo.service.IUserService;
import com.example.demo.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;

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

    // test UI login
    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
