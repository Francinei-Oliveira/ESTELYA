package com.estelya.tenant.infrastructure;

import com.estelya.tenant.domain.Tenant;
import com.estelya.tenant.domain.TenantStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tenants")
public class TenantJpaEntity {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "legal_name", length = 200)
    private String legalName;

    @Column(name = "document", nullable = false, unique = true, length = 30)
    private String document;

    @Column(name = "slug", nullable = false, unique = true, length = 100)
    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private TenantStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected TenantJpaEntity() {
        // Construtor exigido pelo JPA.
    }

    private TenantJpaEntity(
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

    public static TenantJpaEntity fromDomain(Tenant tenant) {
        return new TenantJpaEntity(
                tenant.getId(),
                tenant.getName(),
                tenant.getLegalName(),
                tenant.getDocument(),
                tenant.getSlug(),
                tenant.getStatus(),
                tenant.getCreatedAt(),
                tenant.getUpdatedAt()
        );
    }

    public Tenant toDomain() {
        return new Tenant(
                id,
                name,
                legalName,
                document,
                slug,
                status,
                createdAt,
                updatedAt
        );
    }

    public UUID getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getDocument() {
        return document;
    }
}