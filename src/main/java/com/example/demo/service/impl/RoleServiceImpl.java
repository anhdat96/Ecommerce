package com.example.demo.service.impl;

import com.example.demo.models.Orders;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IOrderService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.RoleDTO;
import com.example.demo.service.mapper.IOrderMapper;
import com.example.demo.service.mapper.IRoleMapper;
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
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepo;
    @Autowired
    private IRoleMapper roleMapper;
    @Autowired
    private IUserRepository userRepo;

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleRepo.save(roleMapper.convertToEntity(roleDTO));

        for (User user : role.getUsers()) {
            if (user != null) {
                user.getRoles().add(role); // đã save vào role_user, nếu thêm userRepo.save(user) thì sẽ thừa
            }
        }

        return roleMapper.convertToDTO(role);
    }

    @Override
    public List<RoleDTO> findAll(Integer page, Integer size) {
        List<RoleDTO> list = new ArrayList<>();
        if (page < 1) {
            throw new IllegalArgumentException("Page must be more than zero!");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Size must be more than zero!");
        }
        PageRequest pageRQ = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "roleID"));
        for (Role role : roleRepo.findAll(pageRQ)) {
            list.add(roleMapper.convertToDTO(role));
        }

        return list;
    }

    @Override
    public RoleDTO findById(Long id) {
        Optional<Role> opt = roleRepo.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("ID " + id + " is not exist");
        }

        return roleMapper.convertToDTO(opt.get());
    }

    @Override
    public RoleDTO update(Long id, RoleDTO roleDTO) {
        this.findById(id);

        Role role = roleMapper.convertToEntity(roleDTO);
        role.setId(id);
        return roleMapper.convertToDTO(roleRepo.save(role));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Role> opt = roleRepo.findById(id);
        if (opt.isPresent()){
            Role role = opt.get();
            for (User user : role.getUsers()){
                user.getRoles().remove(role);
                userRepo.save(user);
            }
            roleRepo.deleteById(id);
        }

    }
}