package com.example.demo.controller;

import com.example.demo.service.IOderdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oder-detail")
public class OderDetailController {

    @Autowired
    IOderdetailService iOderdetailService;


//    @PostMapping("/save")
//    public OderdetailDTO create(@Reque    stBody OderdetailDTO oderdetailDTO) {
//
//        Optional<ProductDTO> productDTO = iProductService.findById(oderdetailDTO.getDetailProductID());
//        Products products = iProductMapper.toEntity(productDTO.get());
//
//        if (productDTO.isPresent()) {
//            OderDetail oderDetail = oderdetailMapperImpl.toEntity(oderdetailDTO);
//            oderDetail.setProducts(products);
//            return iOderdetailService.save(oderdetailMapperImpl.toDto(oderDetail));
//        }
//        return null;
//
//
//    }
}
