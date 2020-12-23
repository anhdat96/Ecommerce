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
    @Column(name = "id")
    private long id;
    @Column(name = "detail_name")
    private String detailName;
    @Column(name = "detail_price")
    private float detailPrice;
    @Column(name = "quantity")
    private int Quantity;


    @ManyToOne //nguyentrong edit
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Products products;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Orders orders;


}
