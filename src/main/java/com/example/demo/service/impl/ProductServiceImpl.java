package com.example.demo.service.impl;

import com.example.demo.models.ProductCategories;
import com.example.demo.models.Products;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductCategoryService;
import com.example.demo.service.IProductService;
import com.example.demo.service.dto.ProductCategoryDTO;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.output.ResponseDTO;
import com.example.demo.service.mapper.IProductCategoryMapper;
import com.example.demo.service.mapper.IProductMapper;
import com.example.demo.service.mapper.OderdetailMapperImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    IProductRepository iProductRepository;


    private final IProductMapper iProductMapper;

    @Autowired
    OderdetailMapperImpl oderdetailMapperImpl;

    @Autowired
    IProductCategoryService iProductCategoryService;


    private final IProductCategoryMapper iProductCategoryMapper;

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
    public ResponseDTO<List<ProductDTO>> findAll(Integer page, Integer size) {
        Pageable p =  PageRequest.of(page,size);
        Page<Products> all = iProductRepository.findAll(p);
        // lấy ra page đầu tiên , mỗi page size  phần tử
            List<Products> content = all.getContent();
            List<ProductDTO> myProductDTOList = content.stream().map(iProductMapper::toDto).collect(Collectors.toList());
            ResponseDTO<List<ProductDTO>> listResponseDTO = new ResponseDTO<>(myProductDTOList);
            listResponseDTO.setPage(all.getNumber());
            listResponseDTO.setSize(all.getSize());
            return listResponseDTO ;
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
