package com.example.demo.repository.impl;

import com.example.demo.repository.custom.UserServiceCustom;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserServiceCustom {

    /*this is just test can not using */

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Long searchByUserID(MultiValueMap<String, String> queryParams) {
        String sql = "select count(U) from User U  where U.userID = 1";
        Map<String, Object> values = new HashMap<>();
        Query query = entityManager.createQuery(sql, Long.class);
        values.forEach(query::setParameter);
        return (Long) query.getSingleResult();
    }
}
