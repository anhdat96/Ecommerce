package com.example.demo.service;

import com.example.demo.service.dto.ProductDTO;

import java.util.Optional;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);

    Optional<ProductDTO> findById(Long id);

    ProductDTO update(ProductDTO productDTO, Long id);

    void delete(Long id);
}
