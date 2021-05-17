package com.example.demo.service.impl;

import com.example.demo.models.User;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.repository.impl.UserRepositoryCustomImpl;
import com.example.demo.service.IUserService;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final IUserMapper iUserMapper;
    @Autowired
    IUserRepository userRepository;
    private final UserRepositoryCustomImpl userRepositoryCustomimpl;
    @Autowired
    IRoleRepository iRoleRepository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.info("Request to save Products :{}", userDTO);
        User user = iUserMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return iUserMapper.toDto(user);
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        log.info("Request to get one User :{}", id);
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            log.info("ID " + id + " is not exist!");
            return null;
        }
        return user.map(iUserMapper::toDto);
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        log.info("Request to update User :{}", id);
        User user = userRepository.findById(userDTO.getUserID()).get();
        if (!(user != null)) {
            log.info("can not find this " + id);
            return null;
        }
        user = iUserMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return iUserMapper.toDto(user);
    }

    @Override
    public Optional<UserDTO> findOneByLoginIgnoreCase(String username) {
        return userRepository.findOneByUserFirstNameIgnoreCase(username).map(iUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.info("Request to delete User : {}", id);
        userRepository.deleteById(id);

    }

    @Override
    public UserDTO findusername(String name) {
        UserDTO userDTO = new UserDTO();
        User user = userRepositoryCustomimpl.search(name);
        userDTO = iUserMapper.toDto(user);
        return userDTO;
    }
}
