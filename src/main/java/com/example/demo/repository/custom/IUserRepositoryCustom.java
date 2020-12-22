package com.example.demo.repository.custom;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@NoRepositoryBean
public interface IUserRepositoryCustom {
    Long countByUserID(MultiValueMap<String, String> queryParams);
}
