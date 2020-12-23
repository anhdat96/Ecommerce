package com.example.demo.controller;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;
import com.example.demo.service.dto.RoleDTO;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.mapper.IRoleMapper;
import com.example.demo.service.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    IRoleService iRoleService;

    @Autowired
    IUserService iUserService;
    private final IRoleMapper iRoleMapper;
    private final IUserMapper iUserMapper;

    @PostMapping(value = "/create")
    public RoleDTO create(@RequestBody RoleDTO roleDTO) {

        return iRoleService.save(roleDTO);
    }

    @GetMapping("/get-one-role/{id}")
    public RoleDTO findOne(@PathVariable Long id) {
        return iRoleService.findById(id).get();
    }

    @PutMapping("/update-role/{id}")
    public RoleDTO update(@RequestBody RoleDTO roleDTO, @PathVariable Long id) {
        return iRoleService.update(roleDTO, id);
    }

    @DeleteMapping(value = "/delete-role/{id}")
    public void delete(@PathVariable Long id) {
        iRoleService.delete(id);
    }


}
