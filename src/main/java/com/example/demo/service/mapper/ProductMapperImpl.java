package com.example.demo.service.mapper;

import com.example.demo.models.Products;
import com.example.demo.service.dto.ProductDTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductMapperImpl implements IProductMapper {



    @Override
    public ProductDTO toDto(Products productEntity) {
        if(productEntity == null){
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductID(productEntity.getProductID());
        productDTO.setProductCode(productEntity.getProductCode());
        productDTO.setProductDescription(productEntity.getProductDescription());
        productDTO.setProductImage(productEntity.getProductImage());
        productDTO.setProductName(productEntity.getProductName());
        productDTO.setProductThumb(productEntity.getProductThumb());
        productDTO.setStatus(productEntity.getStatus());
        productDTO.setProductPrice(productEntity.getProductPrice());
        productDTO.setDetailID(productEntity.getProductID());

        return productDTO;
    }

    @Override
    public Products toEntity(ProductDTO productDTO) {
        if(productDTO == null){
            return null;
        }
        Products products = new Products();

        products.setProductID(productDTO.getProductID());
        products.setProductCode(productDTO.getProductCode());
        products.setProductImage(productDTO.getProductImage());
        products.setProductName(productDTO.getProductName());
        products.setStatus(productDTO.getStatus());
        products.setProductDescription(productDTO.getProductDescription());
        products.setProductPrice(productDTO.getProductPrice());
        products.setProductThumb(productDTO.getProductThumb());

        return products;
    }

    @Override
    public List<Products> toEntity(List<ProductDTO> dtoList) {
        if(dtoList == null){
            return  null;
        }
        List<Products> list = new ArrayList<Products>(dtoList.size());
        for (ProductDTO productDTO : dtoList){
            list.add(toEntity(productDTO));
        }
        return list;

    }

    @Override
    public List<ProductDTO> toDto(List<Products> entityList) {
        if(entityList == null){
            return null;
        }
        List<ProductDTO> list = new ArrayList<ProductDTO>(entityList.size());
        for (Products products:entityList){
            list.add(toDto(products));
        }
        return list;

    }


}
