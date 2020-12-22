package com.example.demo.service;

import com.example.demo.service.dto.RoleDTO;
import com.example.demo.service.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO update(Long id, UserDTO userDTO);

    void deleteById(Long id);
}