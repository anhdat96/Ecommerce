package com.example.demo.controller;

import com.example.demo.service.IRoleService;
import com.example.demo.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    @Qualifier("roleServiceImpl")
    private IRoleService roleService;

    @PostMapping(value = "/create")
    @Transactional
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO roleDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleService.save(roleDTO));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<RoleDTO>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleService.findAll(page, size));
    }

    @GetMapping(value = "/get-one")
    public ResponseEntity<RoleDTO> findById(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleService.findById(id));
    }

    @PutMapping(value = "/update")
    @Transactional
    public ResponseEntity<RoleDTO> update(@RequestParam Long id, @Valid @RequestBody RoleDTO roleDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleService.update(id, roleDTO));
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        roleService.deleteById(id);
    }
}