package com.example.demo.config;

import com.example.demo.models.User;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class   UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    IUserRepository  iuserRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iuserRepository.findByUserFirstName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        // convert user ---> userDetailImpl

        return UserDetailImpl.build(user);
    }
}
