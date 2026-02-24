package com.team3.capstone.backend.entity.enums;

public enum CategoryType {
    TECHNICAL("Technical issues"),
    BILLING("Billing related issues"),
    GENERAL("General queries");

    public final String description;

    CategoryType(String description) {
        this.description = description;
    }
}