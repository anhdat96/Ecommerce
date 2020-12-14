package com.example.demo.service.mapper;

import com.example.demo.models.Products;
import com.example.demo.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {})
public interface IProductMapper extends EntityMapper<ProductDTO, Products> {

}
