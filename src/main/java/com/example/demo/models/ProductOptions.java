package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "productOptions")
@Getter
@Setter
@ToString
public class ProductOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producOptionID;
    private Long optionID;
    private Long productID;
    private String optionPriceIncrement;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "optionID")
    private Options options;
}
