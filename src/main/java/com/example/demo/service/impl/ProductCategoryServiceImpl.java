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
    public List<ProductCategoryDTO> findAll() {
        Set<ProductCategoryDTO> set = new HashSet<>();

        for (ProductCategories productCategory : productCategoryRepo.findAll()) {
            set.add(productCategoryMapper.convertToDTO(productCategory));
        }

        return set.stream()
                .sorted(Comparator.comparing(ProductCategoryDTO::getCategoryID))
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDTO findById(Long id) {
        Optional<ProductCategories> opt = productCategoryRepo.findById(id);
        if (!opt.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
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