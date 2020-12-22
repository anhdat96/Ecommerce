package com.example.demo.service.mapper.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.models.ProductCategories;
import com.example.demo.models.Products;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IProductCategoryRepository;
import com.example.demo.service.dto.*;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProductMapperImpl extends BaseMapper implements IProductMapper {
    @Autowired
    private IProductCategoryRepository productCategoryRepo;
    @Autowired
    private IOrderDetailRepository orderDetailRepo;

    /* convert tu entity -->DTO*/
    @Override
    public ProductDTO convertToDTO(Products input) {
        ProductDTO output = this.tranferData(input, ProductDTO.class);

        //config product
        this.displayConfig(output);

        return output;
    }

    //region cấu hình cách hiển thị thông tin order table
    private void displayConfig(ProductDTO productDTO) {
        // config product category
        ProductCategoryDTO productCategoryDTO = productDTO.getProductCategories();
        if (productCategoryDTO != null) {
            productCategoryDTO.setProductsList(new HashSet<>());
        }

        // config order detail
        Set<OrderDetailDTO> orderDetailDTOSet = productDTO.getOderDetailList();
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOSet) {
            if (null == orderDetailDTO) continue;
            orderDetailDTO.setProducts(null);
            //config order of order detail
            OrderDTO orderDTO = orderDetailDTO.getOrders();
            if (null == orderDTO) continue;
            orderDTO.setOderDetailList(new HashSet<>());
            //config user of order
            UserDTO user = orderDTO.getUser();
            if (null == user) continue;
            user.setOrdersList(new HashSet<>());
            //config role of usr
            for (RoleDTO roleDTO : user.getRoles()){
                if (roleDTO==null) continue;
                roleDTO.setUsers(new HashSet<>());
            }
        }
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public Products convertToEntity(ProductDTO input) {
        Products output = this.tranferData(input, Products.class);

        output.setProductCategories(this.getDataById(input.getProductCategory_id(), productCategoryRepo, ProductCategories.class));
        output.setOderDetailList(this.getDataById(input.getOrderDetail_ids(), orderDetailRepo, OderDetail.class));

        return output;
    }
}