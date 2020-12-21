package com.example.demo.controller;

import com.example.demo.service.IOrderService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    @Qualifier("roleServiceImpl")
    private IRoleService roleService;

    @PostMapping(value = "/create")
    @Transactional
    public RoleDTO create(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @GetMapping(value = "/get-all")
    public List<RoleDTO> findAll() {
        return roleService.findAll();
    }

    @GetMapping(value = "/get-one")
    public RoleDTO findById(@RequestParam Long id) {
        return roleService.findById(id);
    }

    @PutMapping(value = "/update")
    @Transactional
    public RoleDTO update(@RequestParam Long id, @RequestBody RoleDTO roleDTO) {
        return roleService.update(id, roleDTO);
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        roleService.deleteById(id);
    }
}