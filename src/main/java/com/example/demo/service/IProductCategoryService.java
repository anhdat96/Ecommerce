package com.example.demo.service;

import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.dto.UserDTO;

import java.util.List;

public interface IProductCategoryService {
    ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO);

    List<ProductCategoryDTO> findAll(Integer page, Integer size);

    ProductCategoryDTO findById(Long id);

    ProductCategoryDTO update(Long id, ProductCategoryDTO productCategoryDTO);

    void deleteById(Long id);
}