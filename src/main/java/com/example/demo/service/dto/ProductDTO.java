package com.example.demo.service.dto;

import com.example.demo.models.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class ProductDTO implements Serializable {
    private Long productID;
    private String productName;
    private String productPrice;
    private String productImage;
    private Long productCategoryID;
    private String productThumb;
    private String productDescription;
    private String productCode;
    private Integer status;
    private List<Long> orderDetailIds;
    private Long categoryId;
}
