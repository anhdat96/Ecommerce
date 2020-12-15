package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class ProductCategoriesDTO extends AbstractAuditingEntity implements Serializable {
    private long categoryID;
    private String categoryName;
    private List<Long> product_ids;
    private Set<ProductDTO> productsList = new HashSet<>();
}
