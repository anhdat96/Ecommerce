package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class OrderDTO implements Serializable {
    @Null
    private Long id;
    @NotNull
    private Float orderAmount;
    @NotNull
    private String orderShipAddress;
    @NotNull
    private String orderShipName;
    @NotNull
    private String orderCity;
    @NotNull
    private String orderCountry;
    @NotNull
    private String orderPhone;
    @NotNull
    private String orderEmail;
    @NotNull
    private Instant orderDate;
    private List<Long> orderDetailIds;
    private Long userId;
}
