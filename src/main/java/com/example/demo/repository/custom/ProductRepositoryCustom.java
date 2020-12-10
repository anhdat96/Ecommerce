package com.example.demo.repository.custom;


import com.example.demo.models.Products;

import java.util.Optional;

public interface ProductRepositoryCustom {
    Optional<Products> search(Long id);
}
