package com.example.demo.service.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class OrdersDTO {
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

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getOrderUserID() {
        return orderUserID;
    }

    public void setOrderUserID(Long orderUserID) {
        this.orderUserID = orderUserID;
    }

    public Float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderShipAddress() {
        return orderShipAddress;
    }

    public void setOrderShipAddress(String orderShipAddress) {
        this.orderShipAddress = orderShipAddress;
    }

    public String getOrderShipName() {
        return orderShipName;
    }

    public void setOrderShipName(String orderShipName) {
        this.orderShipName = orderShipName;
    }

    public String getOrderCity() {
        return orderCity;
    }

    public void setOrderCity(String orderCity) {
        this.orderCity = orderCity;
    }

    public String getOrderCountry() {
        return orderCountry;
    }

    public void setOrderCountry(String orderCountry) {
        this.orderCountry = orderCountry;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }
}
