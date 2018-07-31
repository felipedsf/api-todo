package com.felipe.todo.security.model;

public class JwtUser {

    private long id;
    private String username;
    private String role;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
