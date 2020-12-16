package com.example.demo.controller;


import com.example.demo.service.IOderdetailService;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.OderdetailDTO;
import com.example.demo.service.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
    @Autowired
    IOderdetailService oderdetailService;

    @Autowired
    IProductService productService;

    @PostMapping(value = "/create")
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @GetMapping("/get-all-product")
    public List<ProductDTO> findall() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        return null;
    }

    @GetMapping("/get-one-product/{id}")
    public ProductDTO findOne(@PathVariable Long id) {
        Optional<ProductDTO> optionalProducts = productService.findById(id);
        if (!optionalProducts.isPresent()) {
            log.error("ID " + id + " is not exist!");
        }
        return optionalProducts.get();
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        ProductDTO productDTO1 = productService.update(productDTO, id);
        return new ResponseEntity<>(productDTO1, HttpStatus.OK);
    }

//    @PostMapping(value = "/save")
//    public ProductDTO save(@RequestBody ProductDTO productDTO) {
//        ProductDTO productDTO1 = productService.save(productDTO);
//        OderdetailDTO oderdetailDTO = new OderdetailDTO();
//        oderdetailDTO.setDetailID(productDTO.getDetailID());
//        oderdetailDTO.setDetailProductID(productDTO.getProductID());
//        oderdetailService.save(oderdetailDTO);
//
////        Optional<OderdetailDTO> oderDetail1 = oderdetailService.findById(productDTO.getDetailID());
//       /* if (!oderDetail1.isPresent()) {
////            productDTO1.setDetailID(oderDetail1.get().getDetailID());
//            oderDetail1.get().setDetailID(productDTO.getDetailID());
//            oderdetailService.save(oderDetail1.get());
//            log.info("save successfully");
//        }*/
//        return productDTO1;
//
//    }

    @DeleteMapping(value = "/delete-product/{id}")
    public void delete(@PathVariable Long id) {
        if (!productService.findById(id).isPresent()) {
            log.error("ID " + id + "is not exist");
            ResponseEntity.badRequest().build();
        } else {
            productService.delete(id);
            log.info("delete product " + id + " successfully");
        }
    }
}
