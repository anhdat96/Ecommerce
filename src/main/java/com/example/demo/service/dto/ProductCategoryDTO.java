package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class ProductCategoryDTO implements Serializable {
    @Null
    private Long id;
    @NotNull
    private String categoryName;
    private List<Long> productIds;
}
