package com.example.demo.service.mapper.impl;

import com.example.demo.models.OderDetail;
import com.example.demo.models.ProductCategories;
import com.example.demo.models.Products;
import com.example.demo.repository.IOrderDetailRepository;
import com.example.demo.repository.IProductCategoryRepository;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

        this.getIdsFromRelationTable(input, output);

        return output;
    }

    private void getIdsFromRelationTable(Products input, ProductDTO output) {
        this.getIdFromProductCategoryTable(input, output);
        this.getIdFromOrderDetailTable(input, output);
    }

    //region get list id from order detail table n product category table
    private void getIdFromOrderDetailTable(Products input, ProductDTO output) {
        output.setOrderDetailIds(new ArrayList<>());

        for (OderDetail detail : input.getOderDetailList()) {
            if (null == detail) continue;
            output.getOrderDetailIds().add(detail.getDetailID());
        }
    }

    private void getIdFromProductCategoryTable(Products input, ProductDTO output) {
        ProductCategories productCategory = input.getProductCategories();

        if (null == productCategory) return;
        output.setProductCategoryId(input.getProductCategories().getCategoryID());
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public Products convertToEntity(ProductDTO input) {
        Products output = this.tranferData(input, Products.class);

        output.setProductCategories(this.getDataById(input.getProductCategoryId(), productCategoryRepo, ProductCategories.class));
        output.setOderDetailList(this.getDataById(input.getOrderDetailIds(), orderDetailRepo, OderDetail.class));

        return output;
    }
}