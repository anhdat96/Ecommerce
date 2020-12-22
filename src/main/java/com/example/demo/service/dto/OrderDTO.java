package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class OrderDTO implements Serializable {
    private Long orderID;
    private Long orderUserID;
    private Float orderAmount;
    private String orderShipAddress;
    private String orderShipName;
    private String orderCity;
    private String orderCountry;
    private String orderPhone;
    private String orderEmail;
    private Instant orderDate;
    private List<Long> orderDetailIds;
    private Long userId;
}
