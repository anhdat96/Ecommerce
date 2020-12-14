package com.example.demo.repository.custom;


import com.example.demo.models.Products;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.List;
@Component
public interface ProductRepositoryCustom {

    //    Long count(MultiValueMap<String, String> queryParams);
    List<Products> search(MultiValueMap<String, String> queryParam);

}
