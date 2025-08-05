package com.pahanaedu.model;

import java.sql.Timestamp;

public class Staff {
    private int id;
    private String username;
    private String password;
    private String role;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Default constructor
    public Staff() {
        this.role = "staff"; // Default role for staff members
    }

    // Constructor without ID (for creating new staff)
    public Staff(String username, String password, String email) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Full constructor
    public Staff(int id, String username, String password, String email,
                 Timestamp createdAt, Timestamp updatedAt) {
        this(username, password, email);
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Helper methods
    public boolean isNew() {
        return this.id == 0;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}