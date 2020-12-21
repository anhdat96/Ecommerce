package com.example.demo.service.mapper;

import com.example.demo.models.Orders;
import com.example.demo.models.Role;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper
public interface IRoleMapper {
    RoleDTO convertToDTO(Role order);

    Role convertToEntity(RoleDTO orderDTO);
}
