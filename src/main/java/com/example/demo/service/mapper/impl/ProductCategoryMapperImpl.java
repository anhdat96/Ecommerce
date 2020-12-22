package com.example.demo.service.mapper.impl;

import com.example.demo.models.ProductCategories;
import com.example.demo.models.Products;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.mapper.BaseMapper;
import com.example.demo.service.mapper.IProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ProductCategoryMapperImpl extends BaseMapper implements IProductCategoryMapper {
    @Autowired
    private IProductRepository productRepo;

    /* convert tu entity -->DTO*/
    @Override
    public ProductCategoryDTO convertToDTO(ProductCategories input) {
        ProductCategoryDTO output = this.tranferData(input, ProductCategoryDTO.class);

        this.getIdsFromRelationTable(input, output);

        return output;
    }

    protected void getIdsFromRelationTable(ProductCategories input, ProductCategoryDTO output) {
        this.getIdFromProductTable(input, output);
    }

    //region get id from product table
    private void getIdFromProductTable(ProductCategories input, ProductCategoryDTO output) {
        output.setProductIds(new ArrayList<>());

        for (Products product : input.getProductsList()) {
            if (null == product) continue;

            output.getProductIds().add(product.getProductID());
        }
    }
    //endregion

    /* convert tu DTO --> Entity*/
    @Override
    public ProductCategories convertToEntity(ProductCategoryDTO input) {
        ProductCategories output = this.tranferData(input, ProductCategories.class);

        output.setProductsList(this.getDataById(input.getProductIds(), productRepo, Products.class));

        return output;
    }
}