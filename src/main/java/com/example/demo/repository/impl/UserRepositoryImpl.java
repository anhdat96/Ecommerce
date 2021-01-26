package com.example.demo.repository.impl;

import com.example.demo.repository.custom.UserRepositoryCustom;
import org.springframework.util.MultiValueMap;


import java.util.HashMap;
import java.util.Map;
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Long count(MultiValueMap<String, String> queryParams) {
        String sql = "select count(U) from User U where U.userID=1 ";
        Map<String, Object> values = new HashMap<>();
        Query query = entityManager.createQuery(sql, Long.class);
        values.forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }
}
