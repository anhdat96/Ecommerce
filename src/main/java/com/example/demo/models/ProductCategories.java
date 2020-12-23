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
@Table(name = "product_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
public class ProductCategories extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "productCategories", fetch = FetchType.EAGER) //nguyentrong edit
    private Set<Products> productsList = new HashSet<>(); //nguyentrong edit
}
