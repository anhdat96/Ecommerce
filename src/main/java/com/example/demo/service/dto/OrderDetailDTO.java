package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class OrderDetailDTO implements Serializable {
    @Null
    private Long id;
    @NotNull
    private String detailName;
    @NotNull
    private Float detailPrice;
    @NotNull
    private Integer Quantity;
    @NotNull
    private Long productId;
    @NotNull
    private Long orderId;
}
