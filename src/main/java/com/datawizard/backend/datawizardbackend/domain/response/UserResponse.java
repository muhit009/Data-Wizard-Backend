package com.datawizard.backend.datawizardbackend.domain.response;

import com.datawizard.backend.datawizardbackend.domain.middle.UserMiddle;

public class UserResponse {
    private int userID;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private String createdAt;
    private String updatedAt;
    private String suspendedAt;
    private String profilePicture;
    private String status;
    private String address;
    private String role;

    public UserResponse(UserMiddle middle){
        if(middle != null) {
            this.userID = middle.getUserID();
            this.email = middle.getEmail();
            this.username = middle.getUsername();
            this.firstName = middle.getFirstName();
            this.lastName = middle.getLastName();
            if (middle.getDateOfBirth() != null) {
                this.dateOfBirth = middle.getDateOfBirth().toString();
            }
            this.phoneNumber = middle.getPhoneNumber();
            if (middle.getCreatedAt() != null) {
                this.createdAt = middle.getCreatedAt().toString();
            }
            if (middle.getUpdatedAt() != null) {
                this.updatedAt = middle.getUpdatedAt().toString();
            }
            if (middle.getSuspendedAt() != null) {
                this.suspendedAt = middle.getSuspendedAt().toString();
            }
            this.profilePicture = middle.getProfilePicture();
            if(middle.getStatus() != null) {
                this.status = middle.getStatus().name();
            }
            this.address = middle.getAddress();
            if(middle.getRole() != null) {
                this.role = middle.getRole().name();
            }
        }
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSuspendedAt() {
        return suspendedAt;
    }

    public void setSuspendedAt(String suspendedAt) {
        this.suspendedAt = suspendedAt;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
