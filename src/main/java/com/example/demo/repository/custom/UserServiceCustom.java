package com.example.demo.repository.custom;

import org.springframework.util.MultiValueMap;

public interface UserServiceCustom {
    Long searchByUserID(MultiValueMap<String, String> queryParams);

}
