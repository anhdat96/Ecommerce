package com.example.demo.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class Products extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private long productID;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productPrice")
    private float productPrice;
    @Column(name = "productImage")
    private String productImage;
    @Column(name = "productThumb")
    private String productThumb;
    @Column(name = "productDescription")
    private String productDescription;
    @Column(name = "productCode")
    private String productCode;
    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER) //nguyentrong edit
    private Set<OderDetail> oderDetailList = new HashSet<>(); //nguyentrong edit

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private ProductCategories productCategories;

}
