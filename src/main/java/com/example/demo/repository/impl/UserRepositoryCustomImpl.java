package com.example.demo.repository.impl;

import com.example.demo.models.User;
import com.example.demo.repository.custom.UserRepositoryCustom;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Component("UserRepositoryCustomImpl")
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User search(String email) {
        Query query =
                entityManager.createQuery("SELECT U FROM User U WHERE LOWER(U.userFirstName) LIKE LOWER(:email)")
                        .setParameter("email", email);
        List<User> list = query.getResultList();
        return list.get(0);
    }
}
