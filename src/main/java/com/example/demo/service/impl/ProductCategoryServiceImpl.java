package com.example.demo.service.impl;

import com.example.demo.models.ProductCategories;
import com.example.demo.repository.IProductCategoryRepository;
import com.example.demo.service.IProductCategoryService;
import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.mapper.IProductCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductCategoryServiceImpl implements IProductCategoryService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    IProductCategoryRepository iProductCategoryRepository;
    @Autowired
    IProductCategoryMapper iProductCategoryMapper;


    @Override
    public ProductCategoryDTO save(ProductCategoryDTO productCategoryDTO) {

        log.debug("request to save ProductCategory ",productCategoryDTO);
        ProductCategories productCategories = iProductCategoryMapper.toEntity(productCategoryDTO);
        iProductCategoryRepository.save(productCategories);
        return iProductCategoryMapper.toDto(productCategories);
    }

    @Override
    public Optional<ProductCategoryDTO> findById(Long id) {
        log.debug("request to get one ProductCategory by id ",id);
        return iProductCategoryRepository.findById(id).map(iProductCategoryMapper::toDto);
    }

    @Override
    public ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO, Long id) {
        log.debug("request to update ProductCategory" , id);
        ProductCategories productCategories = iProductCategoryRepository.findById(id).get();
        if(productCategories != null){
            productCategories = iProductCategoryMapper.toEntity(productCategoryDTO);
            productCategories = iProductCategoryRepository.save(productCategories);
            return iProductCategoryMapper.toDto(productCategories);
        }
        log.debug("can not find this " + id);
        return null;
    }


    @Override
    public void delete(Long id) {
        log.debug("request to delete ProductCategory :{}",id);
        if(!iProductCategoryRepository.findById(id).isPresent()){
            log.error("ID " + id + "is not exist");
        }
        iProductCategoryRepository.deleteById(id);
        log.info("delete product " + id + " successfully");
    }
}
