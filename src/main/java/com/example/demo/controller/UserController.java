package com.example.demo.controller;

import com.example.demo.security.UserDetailImpl;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.payload.request.SignUpRequest;
import com.example.demo.security.payload.response.JwtResponse;
import com.example.demo.service.IUserService;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final IUserService iUserService;
    private final IUserMapper iUserMapper;
    private final UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;


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

    @GetMapping("/loginPage")
    public String loginpage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(SignUpRequest signUpRequest) {
        return "register";
    }

}
