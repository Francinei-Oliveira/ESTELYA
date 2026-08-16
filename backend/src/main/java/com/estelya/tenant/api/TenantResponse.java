package com.estelya.tenant.api;

import com.estelya.tenant.domain.Tenant;

import java.time.Instant;
import java.util.UUID;

public record TenantResponse(
        UUID id,
        String name,
        String legalName,
        String document,
        String slug,
        String status,
        Instant createdAt,
        Instant updatedAt
) {

    public static TenantResponse fromDomain(Tenant tenant) {
        return new TenantResponse(
                tenant.getId(),
                tenant.getName(),
                tenant.getLegalName(),
                tenant.getDocument(),
                tenant.getSlug(),
                tenant.getStatus().name(),
                tenant.getCreatedAt(),
                tenant.getUpdatedAt()
        );
    }
}