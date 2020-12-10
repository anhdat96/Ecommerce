package com.example.demo.service.mapper;

import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.example.demo.service.dto.OrderDTO;
import com.example.demo.service.dto.ProductDTO;
import org.modelmapper.ModelMapper;

/*nguyentrong đề xuất: thêm class abstract Mapper cha bao gồm:
1. attributes: ModelMapper
2. methods:
- convertToDTO()
- convertToEntity()

**note: nếu thêm class abstract Mapper cha thì cần thêm class abstract DTO cha*/
public class OrderMapper {
    ModelMapper modelMapper = new ModelMapper();
   /* convert tu entity -->DTO*/

    public OrderDTO convertToDTO(OrderMapper order){
        OrderDTO orderDTO = modelMapper.map(order,OrderDTO.class);
        return orderDTO;
    }

   /* convert tu DTO --> Entity*/
    public Orders convertToEntity(OrderDTO orderDTO){
        Orders order = modelMapper.map(orderDTO,Orders.class);
        return order;
    }

 /*   // cach 2

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
    }*/
}
