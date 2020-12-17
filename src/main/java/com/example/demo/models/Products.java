package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class Products extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private Long productID;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productPrice")
    private String productPrice;
    @Column(name = "productImage")
    private String productImage;
    @Column(name = "productThumb")
    private String productThumb;
    @Column(name = "productDescription")
    private String productDescription;
    @Column(name = "productCode")
    private String productCode;
    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<OderDetail> oderDetailList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoryID",nullable = false)
    // categoryID là khóa ngoại trươc khi update phai kiểm tra categoryID có tồn tại trong bảng productcategories hay không
    // nếu không có không cho update , thêm trường này trong DTO
    private ProductCategories productCategories;




}
