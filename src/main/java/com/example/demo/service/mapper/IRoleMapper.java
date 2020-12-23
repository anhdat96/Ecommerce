package com.example.demo.service.mapper;

import com.example.demo.models.Role;
import com.example.demo.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {})
public interface IRoleMapper extends EntityMapper<RoleDTO, Role>{
}
