package com.example.demo.service;

import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.output.ResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);

    Optional<ProductDTO> findById(Long id);

    ProductDTO update(ProductDTO productDTO, Long id);

    ResponseDTO<List<ProductDTO>> findAll(Integer page , Integer size);

    void delete(Long id);
}
