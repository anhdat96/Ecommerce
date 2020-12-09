package com.example.demo.repository;

import com.example.demo.models.Products;

import java.util.List;

public interface IProductRepository {
    List<Products> getAllProduct();
}
