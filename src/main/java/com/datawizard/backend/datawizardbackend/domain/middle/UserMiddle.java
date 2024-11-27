package com.datawizard.backend.datawizardbackend.domain.middle;

import com.datawizard.backend.datawizardbackend.domain.UserRole;
import com.datawizard.backend.datawizardbackend.domain.UserStatus;
import com.datawizard.backend.datawizardbackend.domain.request.UserRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UserMiddle {
    private int userID;
    private String email;
    private String passwordHash;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth = null;
    private String phoneNumber;
    private LocalDateTime createdAt = null;
    private LocalDateTime updatedAt = null;
    private LocalDateTime suspendedAt = null;
    private String profilePicture;
    private UserStatus status = UserStatus.ACTIVE;
    private String address;
    private UserRole role = UserRole.JOB_SEEKER;

    public UserMiddle(UserRequest request){
        if(request != null) {
            this.userID = request.getUserID();
            this.email = request.getEmail();
            this.passwordHash = request.getPasswordHash();
            this.username = request.getUsername();
            this.firstName = request.getFirstName();
            this.lastName = request.getLastName();
            if(request.getDateOfBirth() != null) {
                this.dateOfBirth = LocalDate.parse(request.getDateOfBirth());
            }
            this.phoneNumber = request.getPhoneNumber();
            this.address = request.getAddress();
            if(request.getRole() != null) {
                this.role = UserRole.fromName(request.getRole());
            }
        }
    }

    public UserMiddle(ResultSet rs) throws SQLException {
        this.userID = rs.getInt("user_id");
        this.email = rs.getString("Email");
        this.username = rs.getString("username");
        this.firstName = rs.getString("first_name");
        this.lastName = rs.getString("last_name");
        this.dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
        this.phoneNumber = rs.getString("phone_number");
        this.createdAt = rs.getTimestamp("created_at").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        this.updatedAt = rs.getTimestamp("updated_at").toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        Timestamp suspendedAtTimestamp = rs.getTimestamp("suspended_at");
        if(suspendedAtTimestamp != null) {
            this.suspendedAt = suspendedAtTimestamp.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        }
        this.status = UserStatus.fromName(rs.getString("status"));
        this.address = rs.getString("address");
        this.role = UserRole.fromName(rs.getString("role"));
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getSuspendedAt() {
        return suspendedAt;
    }

    public void setSuspendedAt(LocalDateTime suspendedAt) {
        this.suspendedAt = suspendedAt;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
