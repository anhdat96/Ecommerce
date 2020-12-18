package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oderDetail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@ToString

public class OderDetail extends AbstractAuditingEntity implements Serializable {
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
    @JsonIgnore
    private Products products;

    @ManyToOne
    @JoinColumn(name = "orderID",nullable = false)
    @JsonIgnore
    private Orders orders;


}
