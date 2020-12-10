package com.example.demo.repository.impl;

import com.example.demo.models.Products;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.custom.ProductRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Override
    public Optional<Products> search(Long id) {
        return Optional.empty();
    }
}
