package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long productID;
    private String productName;
    private String productPrice;
    private String productImage;
    private Long productCategoryID;
    private String productThumb;
    private String productDescription;
    private String productCode;
    private Integer status;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<OderDetail> oderDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<ProductOptions> productOptionsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private ProductCategories productCategories;

}
