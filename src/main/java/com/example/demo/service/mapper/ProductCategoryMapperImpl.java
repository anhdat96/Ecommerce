package com.example.demo.service.mapper;

import com.example.demo.models.ProductCategories;
import com.example.demo.service.dto.ProductCategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductCategoryMapperImpl implements IProductCategoryMapper{
    @Override
    public ProductCategories toEntity(ProductCategoryDTO productCategoryDTO) {
        if(productCategoryDTO == null){
            return null;
        }
        ProductCategories productCategories = new ProductCategories();
        productCategories.setCategoryID(productCategoryDTO.getCategoryID());
        productCategories.setCategoryName(productCategoryDTO.getCategoryName());
        return productCategories;
    }

    @Override
    public ProductCategoryDTO toDto(ProductCategories productCategories) {
        if(productCategories == null){
            return null;
        }
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setCategoryID(productCategories.getCategoryID());
        productCategoryDTO.setCategoryName(productCategories.getCategoryName());
        return productCategoryDTO;
    }

    @Override
    public List<ProductCategories> toEntity(List<ProductCategoryDTO> dtoList) {
        return null;
    }

    @Override
    public List<ProductCategoryDTO> toDto(List<ProductCategories> entityList) {
        return null;
    }
}
