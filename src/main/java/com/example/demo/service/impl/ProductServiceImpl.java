package com.example.demo.service.impl;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.IProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Set<OrderDetail> set = product.getOrderDetailList();
        for (OrderDetail detail : set) {
            if (detail != null) {
                detail.setProducts(product);
            }
        }
        orderDetailRepo.saveAll(set);
    }
    //endregion

    @Override
    public List<ProductDTO> findAll(Integer page, Integer size) {
        List<ProductDTO> list = new ArrayList<>();
        if (page < 1) {
            throw new IllegalArgumentException("Page must be more than zero!");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Size must be more than zero!");
        }
        PageRequest pageRQ = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "productID"));
        for (Products product : productRepo.findAll(pageRQ)) {
            list.add(productMapper.convertToDTO(product));
        }

        return list;
    }

    @Override
    public ProductDTO findById(Long id) {
        Optional<Products> opt = productRepo.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("ID " + id + " is not exist");
        }

        return productMapper.convertToDTO(opt.get());
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        this.findById(id);

        Products product = productMapper.convertToEntity(productDTO);
        product.setId(id);
        this.updateRelationTable(product);

        return productMapper.convertToDTO(productRepo.save(product));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Products> opt = productRepo.findById(id);
        if (opt.isPresent()) {
            //remove order detail table
            orderDetailRepo.deleteAll(opt.get().getOrderDetailList());

            productRepo.deleteById(id);
        }

    }
}