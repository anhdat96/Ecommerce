package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oderDetail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class OderDetail extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detailID")
    private long detailID;
    @Column(name = "detailName")
    private String detailName;
    @Column(name = "detailPrice")
    private float detailPrice;
    @Column(name = "quantity")
    private int Quantity;


    @ManyToOne //nguyentrong edit
    @JoinColumn(name = "productID", nullable = false)
    private Products products;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Orders orders;


}
