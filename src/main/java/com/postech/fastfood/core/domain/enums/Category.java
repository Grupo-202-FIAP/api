package com.postech.fastfood.core.domain.enums;

public enum Category {
    SANDWICHES("SANDWICHES"),
    SIDES("SIDES"),
    DRINKS("DRINKS"),
    DESSERTS("DESSERTS");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
