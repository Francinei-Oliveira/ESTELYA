package com.estelya.tenant.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataTenantRepository
        extends JpaRepository<TenantJpaEntity, UUID> {

    Optional<TenantJpaEntity> findBySlug(String slug);

    boolean existsByDocument(String document);

    boolean existsBySlug(String slug);
}