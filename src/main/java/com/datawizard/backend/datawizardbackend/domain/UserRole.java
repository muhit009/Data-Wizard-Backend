package com.datawizard.backend.datawizardbackend.domain;

public enum UserRole {
    JOB_SEEKER("Job Seeker"),
    EMPLOYER("Employer");

    private final String name;

    UserRole(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }

    public static UserRole fromName(String name){
        if(name == null){
            return null;
        }
        return switch (name.trim().toLowerCase()) {
            case "job seeker" -> JOB_SEEKER;
            case "employer" -> EMPLOYER;
            default -> null;
        };
    }
}
