package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@ToString
public class Orders extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private Long orderID;
    @Column(name = "orderUserID")
    private Long orderUserID;
    @Column(name = "orderAmount")
    private Float orderAmount;
    @Column(name = "orderShipAddress")
    private String orderShipAddress;
    @Column(name = "orderShipName")
    private String orderShipName;
    @Column(name = "orderCity")
    private String orderCity;
    @Column(name = "orderCountry")
    private String orderCountry;
    @Column(name = "orderPhone")
    private String orderPhone;
    @Column(name = "orderEmail")
    private String orderEmail;
    @Column(name = "orderDate")
    private Instant orderDate;

    @OneToMany(mappedBy = "orders")
    private List<OderDetail> oderDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

}
