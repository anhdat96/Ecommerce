package com.example.demo.service.dto;

import lombok.Data;

@Data
public class ProductCategoryDTO {
    private long categoryID;

    private String categoryName;

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
