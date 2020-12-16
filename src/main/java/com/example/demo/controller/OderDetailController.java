package com.example.demo.controller;

import com.example.demo.models.OderDetail;
import com.example.demo.models.Products;
import com.example.demo.service.IOderdetailService;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.OderdetailDTO;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.IProductMapper;
import com.example.demo.service.mapper.OderdetailMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/oder-detail")
public class OderDetailController {
    @Autowired
    IProductService iProductService;
    @Qualifier("productMapperImpl")
    @Autowired
    IProductMapper iProductMapper;
    @Autowired
    IOderdetailService iOderdetailService;
    @Autowired
    OderdetailMapperImpl oderdetailMapperImpl;


    @PostMapping("/save")
    public OderdetailDTO create(@RequestBody OderdetailDTO oderdetailDTO) {

        Optional<ProductDTO> productDTO = iProductService.findById(oderdetailDTO.getDetailProductID());
        Products products = iProductMapper.toEntity(productDTO.get());

        if (productDTO.isPresent()) {
            OderDetail oderDetail = oderdetailMapperImpl.toEntity(oderdetailDTO);
            oderDetail.setProducts(products);
            return iOderdetailService.save(oderdetailMapperImpl.toDto(oderDetail));
        }
        return null;


    }
}
