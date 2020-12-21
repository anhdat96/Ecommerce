package com.example.demo.service.mapper;

import com.example.demo.models.Role;
import com.example.demo.service.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoleMapperImpl implements IRoleMapper{
    @Override
    public Role toEntity(RoleDTO roleDTO) {
        if(roleDTO == null){
            return null;
        }
        Role role = new Role();
        role.setRoleID(roleDTO.getRoleID());
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        return role;
    }

    @Override
    public RoleDTO toDto(Role role) {
        if(role == null){
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleID(role.getRoleID());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        return roleDTO;
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtoList) {
        return null;
    }

    @Override
    public List<RoleDTO> toDto(List<Role> entityList) {
        return null;
    }
}
