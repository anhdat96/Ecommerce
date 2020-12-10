package com.example.demo.service.mapper;

import com.example.demo.models.Products;
import com.example.demo.service.dto.ProductDTO;
import org.hibernate.mapping.Component;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {})
public interface IProductMapper extends EntityMapper<ProductDTO, Products> {

}
