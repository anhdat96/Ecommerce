package com.example.demo.service.impl;

import com.example.demo.models.Products;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository iProductRepository;

    public List<Products> finAll(){
        return iProductRepository.findAll();
    }

    public Optional<Products> findById(Long id){
        return iProductRepository.findById(id);
    }

    public Products save(Products products){
        return  iProductRepository.save(products);
    }
    public void deleteById(Long id)
    {
        iProductRepository.deleteById(id);
    }
}
