package com.example.demo.service.impl;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import com.example.demo.service.IProductService;
import com.example.demo.service.IUserService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.mapper.IProductMapper;
import com.example.demo.service.mapper.IUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepo;
    @Autowired
    private IProductMapper productMapper;
    @Autowired
    private IOrderDetailRepository orderDetailRepo;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Products product = productRepo.save(productMapper.convertToEntity(productDTO));

        this.updateRelationTable(product);

        return productMapper.convertToDTO(product);
    }

    //region update related tables
    private void updateRelationTable(Products product) {
        Set<OderDetail> set = product.getOderDetailList();
        for (OderDetail detail : set) {
            if (detail != null) {
                detail.setProducts(product);
            }
        }
        orderDetailRepo.saveAll(set);
    }
    //endregion

    @Override
    public List<ProductDTO> findAll() {
        Set<ProductDTO> set = new HashSet<>();

        for (Products product : productRepo.findAll()) {
            set.add(productMapper.convertToDTO(product));
        }

        return set.stream()
                .sorted(Comparator.comparing(ProductDTO::getProductID))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Optional<Products> opt = productRepo.findById(id);
        if (!opt.isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        }

        return productMapper.convertToDTO(opt.get());
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        this.findById(id);

        Products product = productMapper.convertToEntity(productDTO);
        product.setProductID(id);
        this.updateRelationTable(product);

        return productMapper.convertToDTO(productRepo.save(product));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Products> opt = productRepo.findById(id);
        if (opt.isPresent()) {
            //remove order detail table
            orderDetailRepo.deleteAll(opt.get().getOderDetailList());

            productRepo.deleteById(id);
        }

    }
}