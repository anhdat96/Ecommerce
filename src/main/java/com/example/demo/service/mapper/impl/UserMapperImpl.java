package com.example.demo.service.mapper.impl;

import com.example.demo.models.Orders;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapperImpl extends BaseMapper implements IUserMapper {
    @Autowired
    private IRoleRepository roleRepo;
    @Autowired
    private IOrderRepository orderRepo;

    /* convert tu entity -->DTO*/
    @Override
    public UserDTO convertToDTO(User input) {
        UserDTO output = this.tranferData(input, UserDTO.class);

        this.getIdFromRelationTable(input, output);

        return output;
    }


    private void getIdFromRelationTable(User input, UserDTO output) {
        this.getIdFromRoleTable(input, output);
        this.getIdFromOrderTable(input, output);
    }

    //region get id from role table n order table
    private void getIdFromOrderTable(User input, UserDTO output) {
        output.setOrderIds(new ArrayList<>());

        for (Orders order : input.getOrdersList()) {
            if (null == order) continue;
            output.getOrderIds().add(order.getOrderID());
        }
    }

    private void getIdFromRoleTable(User input, UserDTO output) {
        output.setRoleIds(new ArrayList<>());

        for (Role role : input.getRoles()) {
            if (null == role) continue;

            output.getRoleIds().add(role.getRoleID());
        }
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public User convertToEntity(UserDTO input) {
        User output = this.tranferData(input, User.class);

        output.setRoles(this.getDataById(input.getRoleIds(), roleRepo, Role.class));
        output.setOrdersList(this.getDataById(input.getOrderIds(), orderRepo, Orders.class));

        return output;
    }
}