package com.estelya.tenant.domain;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository {

    Tenant save(Tenant tenant);

    Optional<Tenant> findById(UUID id);

    Optional<Tenant> findBySlug(String slug);

    boolean existsByDocument(String document);

    boolean existsBySlug(String slug);
}
