package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oderDetail")
@Getter
@Setter
@ToString
public class OderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="detailID" )
    private Long detailID;
    @Column(name = "detailOder")
    private Long detailOder;
    @Column(name = "detailProductID")
    private Long detailProductID;
    @Column(name = "detailName")
    private String detailName;
    @Column(name = "detailPrice")
    private Float detailPrice;
    @Column(name = "quantity")
    private Integer Quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID",nullable = false)
    private Products products;

    @ManyToOne
    @JoinColumn(name = "orderID",nullable = false)
    private Orders orders;


}
