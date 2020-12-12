package com.example.demo.service.mapper;

import com.example.demo.models.Products;
import com.example.demo.service.dto.ProductDTO;
import org.modelmapper.ModelMapper;

public class ProductMapper {
    ModelMapper modelMapper = new ModelMapper();
   /* convert tu entity -->DTO*/

    public ProductDTO convertToDTO(Products products){
        ProductDTO productDTO = modelMapper.map(products,ProductDTO.class);
        return productDTO;
    }

   /* convert tu DTO --> Entity*/
    public Products convertToEntity(ProductDTO productDTO){
    Products productsEntity = modelMapper.map(productDTO,Products.class);
    return productsEntity;
    }

    // cach 2

    public ProductDTO convertToDTO2(Products productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductID(productEntity.getProductID());
        productDTO.setProductCode(productEntity.getProductCode());
        productDTO.setProductDescription(productEntity.getProductDescription());
        productDTO.setProductImage(productEntity.getProductImage());
        productDTO.setProductName(productEntity.getProductName());
        productDTO.setProductThumb(productEntity.getProductThumb());
        productDTO.setStatus(productEntity.getStatus());
        productDTO.setProductCategoryID(productEntity.getProductCategoryID());
        productDTO.setProductPrice(productEntity.getProductPrice());
        productDTO.setDetailID(productEntity.getProductID());


        return productDTO;
    }
    public Products convertToEntity2(ProductDTO productDTO){
        Products products = new Products();

        products.setProductID(productDTO.getProductID());
        products.setProductCategoryID(productDTO.getProductCategoryID());
        products.setProductCode(productDTO.getProductCode());
        products.setProductImage(productDTO.getProductImage());
        products.setProductName(productDTO.getProductName());
        products.setStatus(productDTO.getStatus());
        products.setProductDescription(productDTO.getProductDescription());
        products.setProductPrice(productDTO.getProductPrice());
        products.setProductThumb(productDTO.getProductThumb());

        return products;
    }






    
}
