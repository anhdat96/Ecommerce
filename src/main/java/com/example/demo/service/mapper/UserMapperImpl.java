package com.example.demo.service.mapper;

import com.example.demo.models.User;
import com.example.demo.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserMapperImpl implements IUserMapper{

    @Override
    public User toEntity(UserDTO userDTO) {
        if(userDTO == null){
            return null;
        }
        User user = new User();
        user.setUserID(userDTO.getUserID());
        user.setUserCity(userDTO.getUserCity());
        user.setUserAddress(userDTO.getUserAddress());
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserCountry(userDTO.getUserCountry());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(userDTO.getGender());
        user.setUserFirstName(userDTO.getUserFirstName());
        user.setUserLastName(user.getUserLastName());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserPhone(userDTO.getUserPhone());
        user.setUserState(userDTO.getUserState());
        return user;
    }

    @Override
    public UserDTO toDto(User user) {
        if(user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(user.getUserID());
        userDTO.setUserCity(user.getUserCity());
        userDTO.setUserAddress(user.getUserAddress());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserCountry(user.getUserCountry());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setGender(user.getGender());
        userDTO.setUserFirstName(user.getUserFirstName());
        userDTO.setUserLastName(user.getUserLastName());
        userDTO.setUserPassword(user.getUserPassword());
        userDTO.setUserPhone(user.getUserPhone());
        userDTO.setUserState(user.getUserState());
        return userDTO;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        return null;
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        return null;
    }
}
