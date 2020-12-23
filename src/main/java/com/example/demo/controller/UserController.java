package com.example.demo.controller;

import com.example.demo.service.IUserService;
import com.example.demo.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private IUserService userService;

    @PostMapping(value = "/create")
    @Transactional
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.save(userDTO));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<UserDTO>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findAll(page, size));
    }

    @GetMapping(value = "/get-one")
    public ResponseEntity<UserDTO> findById(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findById(id));
    }

    @PutMapping(value = "/update")
    @Transactional
    public ResponseEntity<UserDTO> update(@RequestParam Long id, @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.update(id, userDTO));
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        userService.deleteById(id);
    }
}