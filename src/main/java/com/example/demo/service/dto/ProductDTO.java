package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.example.demo.models.ProductCategories;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class ProductDTO extends AbstractAuditingEntity implements Serializable {
    private Long productID;
    private String productName;
    private String productPrice;
    private String productImage;
    private Long productCategoryID;
    private String productThumb;
    private String productDescription;
    private String productCode;
    private Integer status;
    private Set<OrderDetailDTO> oderDetailList = new HashSet<>();
    private ProductCategoriesDTO productCategories;

}
