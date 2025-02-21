package com.belisa.delivery.entity.enums;

public enum ProductCategory {
    TECHNICAL, CLOTHING, FOOD;

    public static ProductCategory getProductCategory(String category) {
        for (ProductCategory productCategory : ProductCategory.values()) {
            if (productCategory.name().equals(category)) {
                return productCategory;
            }
        }
        throw new RuntimeException("Такой категории не существует");
    }
}
