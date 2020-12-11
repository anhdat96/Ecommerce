package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDTO extends AbstractAuditingEntity implements Serializable {
    private Long detailID;
    private Long detailOder;
    private Long detailProductID;
    private String detailName;
    private Float detailPrice;
    private Integer Quantity;
    private Products products;
    private Orders orders;
}
