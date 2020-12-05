package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
@ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "orders")
    private List<OderDetail> oderDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

}
