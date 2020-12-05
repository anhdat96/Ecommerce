package com.example.demo.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@Table(name = "options")
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionID;
    private String optionName;

    @OneToMany(mappedBy = "options")
    private List<ProductOptions> productOptionsList = new ArrayList<>();

}
