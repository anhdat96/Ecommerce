package com.example.demo.repository.custom;

import com.example.demo.models.User;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.MultiValueMap;

public interface UserRepositoryCustom {
    User search(String email);
}
