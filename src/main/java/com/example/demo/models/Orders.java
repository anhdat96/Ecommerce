package com.example.demo.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Data
public class Orders extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private long orderID;
    @Column(name = "orderAmount")
    private float orderAmount;
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

    @OneToMany(mappedBy = "orders", fetch = FetchType.EAGER) //nguyentrong edit
    private Set<OderDetail> oderDetailList = new HashSet<>(); //nguyentrong edit

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
}
