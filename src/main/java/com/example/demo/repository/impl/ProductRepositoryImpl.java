package com.example.demo.repository.impl;

import com.example.demo.models.Products;
import com.example.demo.repository.custom.ProductRepositoryCustom;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    /*the EntityManager instance is associated with a persistence context
     * API EntityManger is used to create and remove persistent entity instances , to find entities by their primary key and to query over entities
     * */
    @Override
    public List<Products> search(MultiValueMap<String, String> queryParam) {
        String sql = " select P from Products P where P.status = 1 ";
        Query query = entityManager.createQuery(sql, Products.class);
        return query.getResultList();
    }
}
