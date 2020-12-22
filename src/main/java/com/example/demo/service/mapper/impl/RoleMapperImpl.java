package com.example.demo.service.mapper.impl;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.dto.RoleDTO;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RoleMapperImpl extends BaseMapper implements IRoleMapper {
    @Autowired
    private IUserRepository userRepo;

    /* convert tu entity -->DTO*/
    @Override
    public RoleDTO convertToDTO(Role input) {
        RoleDTO output = this.tranferData(input, RoleDTO.class);

        this.getIdFromRelationTable(input, output);

        return output;
    }

    private void getIdFromRelationTable(Role input, RoleDTO output) {
        this.getIdFromUserTable(input, output);
    }

    //region get id from user table
    private void getIdFromUserTable(Role input, RoleDTO output) {
        output.setUserIds(new ArrayList<>());
        for (User user : input.getUsers()) {
            if (null == user) continue;

            output.getUserIds().add(user.getUserID());
        }
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public Role convertToEntity(RoleDTO input) {
        Role output = this.tranferData(input, Role.class);

        output.setUsers(this.getDataById(input.getUserIds(), userRepo, User.class));

        return output;
    }
}