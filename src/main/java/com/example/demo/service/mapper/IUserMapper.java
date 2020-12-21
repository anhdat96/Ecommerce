package com.example.demo.service.mapper;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.service.dto.RoleDTO;
import com.example.demo.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface IUserMapper {
    UserDTO convertToDTO(User user);

    User convertToEntity(UserDTO userDTO);
}
