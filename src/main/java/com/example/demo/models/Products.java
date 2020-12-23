package com.example.demo.models;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode(callSuper=false)
@Data
public class Products extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private float productPrice;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "product_thumb")
    private String productThumb;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER) //nguyentrong edit
    private Set<OrderDetail> orderDetailList = new HashSet<>(); //nguyentrong edit

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private ProductCategories productCategories;

}
