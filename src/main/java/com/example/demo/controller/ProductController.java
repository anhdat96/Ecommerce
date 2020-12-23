package com.example.demo.controller;


import com.example.demo.models.Products;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.output.ResponseDTO;
import com.example.demo.service.mapper.ProductMapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {


    @Autowired
    IProductService productService;

    @PostMapping(value = "/create")
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @GetMapping("/get-all-product")
    public ResponseDTO<List<ProductDTO>> findall(@RequestParam(name = "page", required = false, defaultValue = "0 ") Integer page,
                                                 @RequestParam(name = "size ", required = false, defaultValue = "10") Integer size) {
        ResponseDTO<List<ProductDTO>> listResponseDTO = productService.findAll(page,size);
        return listResponseDTO;
    }

    @GetMapping("/get-one-product/{id}")
    public ProductDTO findOne(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @PutMapping("/update-product/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return productService.update(productDTO, id);
    }

    @DeleteMapping(value = "/delete-product/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
