package com.example.demo.service;

import com.example.demo.service.dto.UserDTO;

import java.util.Optional;

public interface IUserService {
    UserDTO save(UserDTO userDTO);

    Optional<UserDTO> findById(Long id);

    UserDTO update(UserDTO userDTO, Long id);

    Optional<UserDTO> findOneByLoginIgnoreCase(String username);

    void delete(Long id);
}
