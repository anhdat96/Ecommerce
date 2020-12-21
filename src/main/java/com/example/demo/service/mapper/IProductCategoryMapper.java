package com.example.demo.service.mapper;

import com.example.demo.models.ProductCategories;
import com.example.demo.models.User;
import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface IProductCategoryMapper {
    ProductCategoryDTO convertToDTO(ProductCategories category);

    ProductCategories convertToEntity(ProductCategoryDTO categoryDTO);
}
