package com.example.demo.service;

import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.RoleDTO;

import java.util.List;

public interface IRoleService {
    RoleDTO save(RoleDTO roleDTO);

    List<RoleDTO> findAll(Integer page, Integer size);

    RoleDTO findById(Long id);

    RoleDTO update(Long id, RoleDTO roleDTO);

    void deleteById(Long id);
}