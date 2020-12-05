package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "oder_detail")
@Getter
@Setter
@ToString
public class OderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailID;
    private Long detailOder;
    private Long detailProductID;
    private String detailName;
    private Float detailPrice;
    private Integer Quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID",nullable = false)
    // một sản phẩm có nhiều orderdetails
    private Products products;

    @ManyToOne
    @JoinColumn(name = "orderID",nullable = false)
    private OderDetail orders;


}
