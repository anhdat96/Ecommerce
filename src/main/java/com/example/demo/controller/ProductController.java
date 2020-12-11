package com.example.demo.controller;

import com.example.demo.models.Products;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

//    @GetMapping(value = "/all_Products")
//    public List<Products> findAll() {
//        return productServiceImpl.finAll();
//    }

    @PostMapping(value = "/create")
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productServiceImpl.save(productDTO);
    }

    @GetMapping("/get-one-product/{id}")
    public ProductDTO findOne(@PathVariable Long id) {
        Optional<ProductDTO> optionalProducts = productServiceImpl.findById(id);
        if (optionalProducts.isPresent()) {
            log.error("ID " + id + " is not exist!");
        }
        return optionalProducts.get();

        //.get() sẽ trả về giá trị trong Optional chính là ProductEntity
    }
    @PutMapping("/update-product/{id}")
    public ResponseEntity<ProductDTO>  update(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        ProductDTO productDTO1 = productServiceImpl.update(productDTO,id);
//        return ResponseEntity.ok(productDTO1);
        return new ResponseEntity<>(productDTO1,HttpStatus.OK);
    }


//    @PutMapping(value = "/update/{id}")
//    public Products update(@PathVariable Long id,@RequestBody Products products){
//        if(!productServiceImpl.findById(id).isPresent()){
//            log.error("ID "+ id + "is not exist");
//        }
//        return productServiceImpl.save(products);
//    }
//
//    @DeleteMapping(value = "/delete-product/{id}")
//    public void delete(@PathVariable Long id){
//        if(!productServiceImpl.findById(id).isPresent()){
//            log.error("ID "+ id + "is not exist");
//            ResponseEntity.badRequest().build();
//        }
//        productServiceImpl.deleteById(id);
//
//    }
}
