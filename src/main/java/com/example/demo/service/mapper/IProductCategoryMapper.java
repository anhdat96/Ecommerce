package com.example.demo.service.mapper;

import com.example.demo.models.ProductCategories;
import com.example.demo.service.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface IProductCategoryMapper extends EntityMapper<ProductCategoryDTO , ProductCategories>{
}
