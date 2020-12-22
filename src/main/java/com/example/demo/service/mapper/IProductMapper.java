package com.example.demo.service.mapper;

import com.example.demo.models.ProductCategories;
import com.example.demo.models.Products;
import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface IProductMapper {
    ProductDTO convertToDTO(Products product);

    Products convertToEntity(ProductDTO productDTO);
}
