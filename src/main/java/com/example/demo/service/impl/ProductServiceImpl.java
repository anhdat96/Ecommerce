package com.example.demo.service.impl;

import com.example.demo.models.ProductCategories;
import com.example.demo.models.Products;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductCategoryService;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.IProductCategoryMapper;
import com.example.demo.service.mapper.IProductMapper;
import com.example.demo.service.mapper.OderdetailMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    IProductRepository iProductRepository;

    @Qualifier("productMapperImpl")
    @Autowired
    IProductMapper iProductMapper;

    @Autowired
    OderdetailMapperImpl oderdetailMapperImpl;

    @Autowired
    IProductCategoryService iProductCategoryService;

    @Autowired
    IProductCategoryMapper iProductCategoryMapper;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.info("Request to save Products :{}", productDTO);
        Optional<ProductCategoryDTO> productCategoryDTO = iProductCategoryService.findById(productDTO.getProductCategoryID());

        if (productCategoryDTO.isPresent()) {
            ProductCategories productCategories = iProductCategoryMapper.toEntity(productCategoryDTO.get());
            Products products = iProductMapper.toEntity(productDTO);
            products.setProductCategories(productCategories);
            products = iProductRepository.save(products);
            return iProductMapper.toDto(products);
        }
        log.info("can not save because this ProductCategoryID " + productDTO.getProductCategoryID() + " is not exist in table ProductCategories ");
        return null;

    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        log.info("Request to get one Product :{}", id);
        Optional<Products> optionalProducts = iProductRepository.findById(id);
        if (!optionalProducts.isPresent()) {
            log.info("ID " + id + " is not exist!");
            return null;
        }
        return optionalProducts.map(iProductMapper::toDto);

    }


    @Override
    public ProductDTO update(ProductDTO productDTO, Long id) {
        log.info("Request to update Product :{}", id);
        Optional<Products> products1 = iProductRepository.findById(productDTO.getProductID());
        if (products1.isPresent() ) {
            Optional<ProductCategoryDTO> productCategoryDTO = iProductCategoryService.findById(productDTO.getProductCategoryID());
            if(productCategoryDTO.isPresent()){
                ProductCategories productCategories = iProductCategoryMapper.toEntity(productCategoryDTO.get());
                Products products = iProductMapper.toEntity(productDTO);
                products.setProductCategories(productCategories);
                   Products products2 =  iProductRepository.save(products);
                return iProductMapper.toDto(products2);
            }
            log.info("can not save because this ProductCategoryID " + productDTO.getProductCategoryID() + " is not exist in table ProductCategories ");
            return null;

        }
        log.info("can not find this " + id);
        return null;
    }

    @Override
    public void delete(Long id) {
        if (!iProductRepository.findById(id).isPresent()) {
            log.info("ID " + id + "is not exist");
        } else {
            iProductRepository.deleteById(id);
            log.info("delete product " + id + " successfully");
        }

    }
}
