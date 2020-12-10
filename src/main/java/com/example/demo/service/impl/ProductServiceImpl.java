package com.example.demo.service.impl;

import com.example.demo.models.Products;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.IProductMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements IProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final IProductRepository iProductRepository;
    private final IProductMapper iProductMapper;

//    public List<Products> finAll(){
//        return iProductRepository.findAll();
//    }
//
//    public Optional<Products> findById(Long id){
//        return iProductRepository.findById(id);
//    }
//
//    public Products save(Products products){
//        return  iProductRepository.save(products);
//    }
//    public void deleteById(Long id)
//    {
//        iProductRepository.deleteById(id);
//    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Products :{}",productDTO);
        // convet from DTO --> Entity
        // after save the new product to Database have to return it to client
        // convert Entity -->Dto
        Products products = iProductMapper.toEntity(productDTO);
        products=iProductRepository.save(products);
        return iProductMapper.toDto(products);

    }
}
