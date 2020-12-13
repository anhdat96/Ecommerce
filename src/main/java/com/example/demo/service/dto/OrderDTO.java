package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.example.demo.models.OderDetail;
import com.example.demo.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
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
    private List<Long> orderDetailIds;
    private Set<OderDetail> oderDetailList = new HashSet<>();
    private Long user_id;
    private User user;
}
