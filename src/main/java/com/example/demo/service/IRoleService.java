package com.example.demo.service;

import com.example.demo.service.dto.RoleDTO;

import java.util.Optional;

public interface IRoleService {
    RoleDTO save(RoleDTO roleDTO);

    Optional<RoleDTO> findById(Long id);

    RoleDTO update(RoleDTO roleDTO, Long id);

    void delete(Long id);
}
