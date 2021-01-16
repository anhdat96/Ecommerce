package com.example.demo.service.impl;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IRoleService;
import com.example.demo.service.dto.RoleDTO;
import com.example.demo.service.mapper.IRoleMapper;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService{
    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final IRoleMapper iRoleMapper;
    @Autowired
    IRoleRepository iRoleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        log.info("Request to save Role :{}", roleDTO);

        Role role  = iRoleMapper.toEntity(roleDTO);
        if(roleDTO.getUserId() != null){
            List<User> allById = userRepository.findAllById(roleDTO.getUserId());
            role.setUsers(allById);
        }

        role= iRoleRepository.save(role);
        return iRoleMapper.toDto(role);
    }

    @Override
    public Optional<RoleDTO> findById(Long id) {
        log.info("Request to get one Role :{}", id);
        Optional<Role> role = iRoleRepository.findById(id);
        if(!role.isPresent()){
            log.info("ID " + id + " is not exist!");
            return null;
        }
        return role.map(iRoleMapper::toDto);
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO, Long id) {
        log.info("Request to update one Role :{}", id);
        Role role = iRoleRepository.findById(id).get();
        if(role != null){
            role = iRoleMapper.toEntity(roleDTO);
            role = iRoleRepository.save(role);
            return iRoleMapper.toDto(role);
        }
        log.info("can not find this " + id);
        return null;
    }

    @Override
    public void delete(Long id) {
        iRoleRepository.deleteById(id);

    }
}
