package com.example.demo.service.mapper;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        user.setUserLastName(userDTO.getUserLastName());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserPhone(userDTO.getUserPhone());
        user.setUserState(userDTO.getUserState());
        // khi luư xuống sẽ nhận được 1 danh sách id roles --> từ đó phải set giá tri ngược lại sang objectRole
        List<Role> roles = this.authoritiesFromRoleId(userDTO.getRoleIds());
        user.setRoles(roles);
        return user;
    }
    private List<Role> authoritiesFromRoleId(List<Long> roleIds){
        List<Role> role = new ArrayList<>();
        // khai báo 1 list role rỗng


        if(roleIds != null){
            // lặp qua các phần tử của list roleIds từ client truyền vào bằng stream
            // với mỗi id thuộc list đem set giá trị cho 1 role1 rồi add vào list role
            role = roleIds.stream().map(id -> {
                Role role1 = new Role();
                role1.setRoleID(id);
                return role1;
            }).collect(Collectors.toList());
        }
        return role;
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

        Iterator roles  = user.getRoles().stream().map(Role::getRoleID).collect(Collectors.toList()).iterator();
        List<Long> result = new ArrayList<>();
        while(roles.hasNext()){
            result.add((Long) roles.next());
        }
        userDTO.setRoleIds(result);
        return userDTO;
    }

    @Override
    public List<User> toEntity(List<UserDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> toDto(List<User> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }
}
