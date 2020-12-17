package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class OrderDetailDTO implements Serializable {
    private Long detailID;
    private Long detailOder;
    private Long detailProductID;
    private String detailName;
    private Float detailPrice;
    private Integer Quantity;
    private Long product_id;
    private ProductDTO products;
    private Long order_id;
    private OrderDTO orders;
}
