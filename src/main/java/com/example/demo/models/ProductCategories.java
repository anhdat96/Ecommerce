package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@ToString
public class ProductCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryID")
    private long categoryID;
    @Column(name = "categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "productCategories",cascade = CascadeType.ALL)
    private List<Products> productsList = new ArrayList<>();
}
