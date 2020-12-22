package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ProductDTO implements Serializable {
    @Null
    private Long productID;
    @NotNull
    private String productName;
    @NotNull
    private String productPrice;
    @NotNull
    private String productImage;
    @NotNull
    private Long productCategoryID;
    @NotNull
    private String productThumb;
    @NotNull
    private String productDescription;
    @NotNull
    private String productCode;
    @NotNull
    private Integer status;
    private List<Long> orderDetailIds;
    private Long categoryId;
}
