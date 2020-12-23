package com.example.demo.models;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class Orders extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "order_amount")
    private float orderAmount;
    @Column(name = "order_ship_address")
    private String orderShipAddress;
    @Column(name = "order_ship_name")
    private String orderShipName;
    @Column(name = "order_city")
    private String orderCity;
    @Column(name = "order_country")
    private String orderCountry;
    @Column(name = "order_phone")
    private String orderPhone;
    @Column(name = "order_email")
    private String orderEmail;
    @Column(name = "order_date")
    private Instant orderDate;

    @OneToMany(mappedBy = "orders", fetch = FetchType.EAGER) //nguyentrong edit
    private Set<OrderDetail> orderDetailList = new HashSet<>(); //nguyentrong edit

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
