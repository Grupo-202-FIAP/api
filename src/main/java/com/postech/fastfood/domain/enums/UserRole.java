package com.postech.fastfood.core.domain.enums;

public enum UserRole {
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_KITCHEN("ROLE_KITCHEN"),
    ROLE_DELIVERY("ROLE_DELIVERY"),
    ROLE_GUEST("ROLE_GUEST");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
