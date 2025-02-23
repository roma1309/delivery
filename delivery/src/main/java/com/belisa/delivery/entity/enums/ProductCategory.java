package com.belisa.delivery.entity.enums;

import com.belisa.delivery.exceptions.CategoryNotMatch;

public enum ProductCategory {
    TECHNICAL, CLOTHING, FOOD;

    public static ProductCategory getProductCategory(String category) {
        for (ProductCategory productCategory : ProductCategory.values()) {
            if (productCategory.name().equals(category)) {
                return productCategory;
            }
        }
        throw new CategoryNotMatch("Такой категории не существует");
    }
}
