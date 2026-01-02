package com.archivage.demo.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection ="users")


public class User {
    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private boolean active;
    private boolean isPremium;
    private LocalDateTime subscriptionEnd;

    public User() {
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public User(String username, String email, String password, boolean active, LocalDateTime subscriptionEnd) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.subscriptionEnd = subscriptionEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public void setSubscriptionEnd(LocalDateTime subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;
    }
}


