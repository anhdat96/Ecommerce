package com.example.demo.service.mapper.impl;

import com.example.demo.models.ProductCategories;
import com.example.demo.models.Products;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.dto.*;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProductCategoryMapperImpl extends BaseMapper implements IProductCategoryMapper {
    @Autowired
    private IProductRepository productRepo;

    /* convert tu entity -->DTO*/
    @Override
    public ProductCategoryDTO convertToDTO(ProductCategories input) {
        ProductCategoryDTO output = this.tranferData(input, ProductCategoryDTO.class);

        //config product
        this.displayConfig(output);

        return output;
    }

    //region cấu hình cách hiển thị thông tin order table
    private void displayConfig(ProductCategoryDTO productCategoryDTO) {
        // config role of user
        for (ProductDTO productDTO : productCategoryDTO.getProductsList()) {
            if (productDTO != null) {
                productDTO.setProductCategories(null);

                for (OrderDetailDTO detailDTO : productDTO.getOderDetailList()) {
                    if (detailDTO != null) {
                        detailDTO.setProducts(null);

                        OrderDTO orderDTO = detailDTO.getOrders();
                        if (orderDTO != null) {
                            orderDTO.setOderDetailList(new HashSet<>());

                            UserDTO userDTO = orderDTO.getUser();
                            if (userDTO != null) {
                                userDTO.setOrdersList(new HashSet<>());

                                Set<RoleDTO> roleDTOSet = userDTO.getRoles();
                                for (RoleDTO roleDTO : roleDTOSet) {
                                    if (roleDTO != null) {
                                        roleDTO.setUsers(new HashSet<>());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public ProductCategories convertToEntity(ProductCategoryDTO input) {
        ProductCategories output = this.tranferData(input, ProductCategories.class);

        output.setProductsList(this.getDataById(input.getProduct_ids(), productRepo, Products.class));

        return output;
    }
}