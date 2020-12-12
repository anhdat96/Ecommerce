package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.example.demo.models.OderDetail;
import com.example.demo.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    private List<OderDetail> oderDetailList = new ArrayList<>();
    private User user;
}
