package com.postech.fastfood.core.domain.enums;

public enum UserRole {
    CUSTOMER("ROLE_CUSTOMER"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER"),
    KITCHEN("ROLE_KITCHEN"),
    DELIVERY("ROLE_DELIVERY"),
    GUEST("ROLE_GUEST");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
