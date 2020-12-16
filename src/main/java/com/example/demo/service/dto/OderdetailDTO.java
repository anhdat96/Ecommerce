package com.example.demo.service.dto;

import com.example.demo.models.Orders;
import com.example.demo.models.Products;
import lombok.Data;

@Data
public class OderdetailDTO {
    private Long detailID;

    private Long detailOder;

    private Long detailProductID;

    private String detailName;

    private Float detailPrice;

    private Integer Quantity;

    private Products products;

    private Orders orders;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
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

    public Long getDetailProductID() {
        return detailProductID;
    }

    public void setDetailProductID(Long detailProductID) {
        this.detailProductID = detailProductID;
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
