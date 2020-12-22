package com.example.demo.service.dto;

import lombok.Data;

@Data
public class OderdetailDTO {
    private Long detailID;

    private Long detailOder;

    private String detailName;

    private Float detailPrice;

    private Integer Quantity;

    private Long productId;

    private long ordersId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getDetailID() {
        return detailID;
    }

    public void setDetailID(Long detailID) {
        this.detailID = detailID;
    }

    public Long getDetailOder() {
        return detailOder;
    }

    public void setDetailOder(Long detailOder) {
        this.detailOder = detailOder;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public Float getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(Float detailPrice) {
        this.detailPrice = detailPrice;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
