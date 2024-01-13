package com.example.movie.model.enums;

public enum UserRole {
    ADMIN("Admin"),
    USER("User");
    private final String value;
    UserRole(String value){
        this.value = value;
    }
}
