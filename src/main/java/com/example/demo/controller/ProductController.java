package com.example.demo.controller;

import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    @Qualifier("productServiceImpl")
    private IProductService productService;

    @PostMapping(value = "/create")
    @Transactional
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.save(productDTO));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<ProductDTO>> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findAll(page, size));
    }

    @GetMapping(value = "/get-one")
    public ResponseEntity<ProductDTO> findById(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findById(id));
    }

    @PutMapping(value = "/update")
    @Transactional
    public ResponseEntity<ProductDTO> update(@RequestParam Long id, @Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.update(id, productDTO));
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        productService.deleteById(id);
    }
}