package com.example.demo.service;

import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.dto.ProductDTO;

import java.util.Optional;

public interface IProductCategoryService {
    ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO);
    Optional<ProductCategoryDTO> findById(Long id);
    ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO,Long id);
    void delete(Long id);
}
