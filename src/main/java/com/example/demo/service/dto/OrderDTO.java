package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO extends AbstractAuditingEntity implements Serializable {
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
}

