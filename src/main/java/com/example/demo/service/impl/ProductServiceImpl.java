package com.example.demo.service.impl;

import com.example.demo.models.Products;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.IProductMapper;
import com.example.demo.service.mapper.OderdetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    IProductRepository iProductRepository;
    //    private final IProductRepository iProductRepository;
    @Autowired
    IProductMapper iProductMapper;
    //    private final IProductMapper iProductMapper;
    @Autowired
    OderdetailMapper oderdetailMapper;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Products :{}", productDTO);
        // convet from DTO --> Entity
        // after save the new product to Database have to return it to client
        // convert Entity -->Dto
        Products products = iProductMapper.toEntity(productDTO);
        products = iProductRepository.save(products);
        return iProductMapper.toDto(products);
    }

    @Override
    public Optional<ProductDTO>  findById(Long id) {
        log.debug("Request to get Product :{}", id);
        return iProductRepository.findById(id).map(iProductMapper::toDto);
        //Optional là 1 contaniner Object bao bọc một Object , khi object là null thì Optional trả về empty
        // lấy được Optional<ProductDTO> của <ProductEntity>Optinal
        //.map() trả về giá trị được map , convert tương ứng
    }


    @Override
    public ProductDTO update(ProductDTO productDTO, Long id) {
        log.debug("Request to update Product :{}", id);
        Products products1 = iProductRepository.findById(id).get();
        if (products1.getProductID() == id) {
            products1 = iProductMapper.toEntity(productDTO);
            products1 = iProductRepository.save(products1);
            return iProductMapper.toDto(products1);
        }
        log.debug("can not find this " + id);
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
