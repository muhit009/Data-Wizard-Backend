package com.datawizard.backend.datawizardbackend.domain;

public enum UserStatus {
    ACTIVE("active"),
    SUSPENDED("suspended");

    private final String name;

    UserStatus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }

    public static UserStatus fromName(String name){
        if(name == null){
            return null;
        }
        return switch (name.trim().toLowerCase()) {
            case "active" -> ACTIVE;
            case "suspended" -> SUSPENDED;
            default -> null;
        };
    }
}
