package com.example.demo.service.impl;

import com.example.demo.models.ProductCategories;
import com.example.demo.models.Products;
import com.example.demo.repository.IProductCategoryRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductCategoryService;
import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.mapper.IProductCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductCategoryServiceImpl implements IProductCategoryService {
    @Autowired
    private IProductCategoryRepository productCategoryRepo;
    @Autowired
    private IProductRepository productRepo;
    @Autowired
    private IProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO) {
        ProductCategories productCategory = productCategoryRepo.save(productCategoryMapper.convertToEntity(productCategoryDTO));

        this.updateRelationTable(productCategory);

        return productCategoryMapper.convertToDTO(productCategory);
    }

    //region update related tables
    private void updateRelationTable(ProductCategories productCategory) {
        Set<Products> set = productCategory.getProductsList();
        for (Products products : set) {
            products.setProductCategories(productCategory);
        }

        productRepo.saveAll(set);
    }
    //endregion

    @Override
    public List<ProductCategoryDTO> findAll(Integer page, Integer size) {
        List<ProductCategoryDTO> list = new ArrayList<>();
        if (page < 1) {
            throw new IllegalArgumentException("Page must be more than zero!");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Size must be more than zero!");
        }
        PageRequest pageRQ = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "categoryID"));
        for (ProductCategories productCategory : productCategoryRepo.findAll(pageRQ)) {
            list.add(productCategoryMapper.convertToDTO(productCategory));
        }

        return list;
    }

    @Override
    public ProductCategoryDTO findById(Long id) {
        Optional<ProductCategories> opt = productCategoryRepo.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("ID " + id + " is not exist");
        }

        return productCategoryMapper.convertToDTO(opt.get());
    }

    @Override
    public ProductCategoryDTO update(Long id, ProductCategoryDTO productCategoryDTO) {
        this.findById(id);

        ProductCategories productCategory = productCategoryMapper.convertToEntity(productCategoryDTO);
        productCategory.setCategoryID(id);
        this.updateRelationTable(productCategory);

        return productCategoryMapper.convertToDTO(productCategoryRepo.save(productCategory));
    }

    @Override
    public void deleteById(Long id) {
        Optional<ProductCategories> opt = productCategoryRepo.findById(id);
        if (opt.isPresent()) {
            //remove role table
            ProductCategories productCategory = opt.get();
            this.removeRelationTable(productCategory);

            productCategoryRepo.deleteById(id);
        }

    }

    private void removeRelationTable(ProductCategories productCategory) {
        Set<Products> productSet = productCategory.getProductsList();
        for (Products product : productSet) {
            product.setProductCategories(null);
        }
        productRepo.saveAll(productSet);
    }
}