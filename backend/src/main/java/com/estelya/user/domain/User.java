package com.estelya.user.domain;

import java.time.Instant;
import java.util.UUID;

public class User {

    private final UUID id;
    private final UUID tenantId;
    private String name;
    private String email;
    private String passwordHash;
    private UserRole role;
    private UserStatus status;
    private final Instant createdAt;
    private Instant updatedAt;

    public User(
            UUID id,
            UUID tenantId,
            String name,
            String email,
            String passwordHash,
            UserRole role,
            UserStatus status,
            Instant createdAt,
            Instant updatedAt) {

        this.id = id;
        this.tenantId = tenantId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User create(
            UUID tenantId,
            String name,
            String email,
            String passwordHash,
            UserRole role) {

        Instant now = Instant.now();

        return new User(
                UUID.randomUUID(),
                tenantId,
                name,
                email.toLowerCase().trim(),
                passwordHash,
                role,
                UserStatus.ACTIVE,
                now,
                now
        );
    }

    public boolean isActive() {
        return status == UserStatus.ACTIVE;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}