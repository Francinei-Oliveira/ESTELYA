package com.estelya.user.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByTenantIdAndEmail(
            UUID tenantId,
            String email
    );

    boolean existsByTenantIdAndEmail(
            UUID tenantId,
            String email
    );
}