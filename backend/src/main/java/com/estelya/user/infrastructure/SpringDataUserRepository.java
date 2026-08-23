package com.estelya.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository
        extends JpaRepository<UserJpaEntity, UUID> {

    Optional<UserJpaEntity> findByTenantIdAndEmailIgnoreCase(
            UUID tenantId,
            String email
    );

    boolean existsByTenantIdAndEmailIgnoreCase(
            UUID tenantId,
            String email
    );
}