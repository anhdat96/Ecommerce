package com.example.demo.service;

import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.UserDTO;

import java.util.List;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);

    List<ProductDTO> findAll(Integer page, Integer size);

    ProductDTO findById(Long id);

    ProductDTO update(Long id, ProductDTO productDTO);

    void deleteById(Long id);
}