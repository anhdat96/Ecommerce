package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class OrderDetailDTO extends AbstractAuditingEntity implements Serializable {
    private Long detailID;
    private Long detailOder;
    private Long detailProductID;
    private String detailName;
    private Float detailPrice;
    private Integer Quantity;
    private Long product_id;
    private Products products;
    private Long order_id;
    private Orders orders;
}
