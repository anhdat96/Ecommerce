package com.example.demo.service.mapper;

import com.example.demo.models.User;
import com.example.demo.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {})
public interface IUserMapper extends EntityMapper<UserDTO , User>{
}
