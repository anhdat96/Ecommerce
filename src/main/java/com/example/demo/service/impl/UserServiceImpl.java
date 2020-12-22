package com.example.demo.service.impl;

import com.example.demo.models.Orders;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IUserService;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.mapper.IUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private IOrderRepository orderRepo;
    @Autowired
    private IRoleRepository roleRepo;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userRepo.save(userMapper.convertToEntity(userDTO));

        this.updateRelationTable(user);

        return userMapper.convertToDTO(user);
    }

    //region update related tables
    private void updateRelationTable(User user) {
        this.updateRoleTable(user);
        this.updateOrderTable(user);
    }

    private void updateOrderTable(User user) {
        Set<Orders> set = user.getOrdersList();
        for (Orders order : set) {
            if (order != null) {
                order.setUser(user);
            }
        }
        orderRepo.saveAll(set);
    }

    private void updateRoleTable(User user) {
        for (Role role : user.getRoles()) {
            if (role != null) {
                role.getUsers().add(user);
            }
        }
    }
    //endregion

    @Override
    public List<UserDTO> findAll(Integer page, Integer size) {
        List<UserDTO> list = new ArrayList<>();
        if (page < 1) {
            throw new IllegalArgumentException("Page must be more than zero!");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Size must be more than zero!");
        }
        PageRequest pageRQ = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "userID"));
        for (User user : userRepo.findAll(pageRQ)) {
            list.add(userMapper.convertToDTO(user));
        }

        return list;
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> opt = userRepo.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("ID " + id + " is not exist");
        }

        return userMapper.convertToDTO(opt.get());
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        this.findById(id);

        User user = userMapper.convertToEntity(userDTO);
        user.setUserID(id);
        this.updateRelationTable(user);

        return userMapper.convertToDTO(userRepo.save(user));
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> opt = userRepo.findById(id);
        if (opt.isPresent()) {
            //remove role table
            User user = opt.get();
            this.removeRelationTable(user);

            userRepo.deleteById(id);
        }

    }

    private void removeRelationTable(User user) {
        // remove role table
        this.removeRoleTable(user);
        // remove order table
        this.removeOrderTable(user);
    }

    private void removeOrderTable(User user) {
        Set<Orders> orderSet = user.getOrdersList();
        for (Orders order : orderSet) {
            order.setUser(null);
        }
        orderRepo.saveAll(orderSet);
    }

    private void removeRoleTable(User user) {
        for (Role role : user.getRoles()) {
            role.getUsers().remove(user);
        }
        roleRepo.saveAll(user.getRoles());
    }
}