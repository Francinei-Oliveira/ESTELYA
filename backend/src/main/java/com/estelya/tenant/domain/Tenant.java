package com.estelya.tenant.domain;

import java.time.Instant;
import java.util.UUID;

public class Tenant {

    private final UUID id;
    private String name;
    private String legalName;
    private String document;
    private String slug;
    private TenantStatus status;
    private final Instant createdAt;
    private Instant updatedAt;

    public Tenant(
            UUID id,
            String name,
            String legalName,
            String document,
            String slug,
            TenantStatus status,
            Instant createdAt,
            Instant updatedAt) {

        this.id = id;
        this.name = name;
        this.legalName = legalName;
        this.document = document;
        this.slug = slug;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Tenant create(
            String name,
            String legalName,
            String document,
            String slug) {

        Instant now = Instant.now();

        return new Tenant(
                UUID.randomUUID(),
                name,
                legalName,
                document,
                slug,
                TenantStatus.ACTIVE,
                now,
                now
        );
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getDocument() {
        return document;
    }

    public String getSlug() {
        return slug;
    }

    public TenantStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
