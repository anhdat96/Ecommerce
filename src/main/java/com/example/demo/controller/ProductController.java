package com.example.demo.controller;

import com.example.demo.service.IProductService;
import com.example.demo.service.IUserService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    @Qualifier("productServiceImpl")
    private IProductService productService;

    @PostMapping(value = "/create")
    @Transactional
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @GetMapping(value = "/get-all")
    public List<ProductDTO> findAll(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        return productService.findAll(page, size);
    }

    @GetMapping(value = "/get-one")
    public ProductDTO findById(@RequestParam Long id) {
        return productService.findById(id);
    }

    @PutMapping(value = "/update")
    @Transactional
    public ProductDTO update(@RequestParam Long id, @RequestBody ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam Long id) {
        productService.deleteById(id);
    }
}