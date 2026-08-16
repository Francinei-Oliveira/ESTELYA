package com.estelya.tenant.infrastructure;

import com.estelya.tenant.domain.Tenant;
import com.estelya.tenant.domain.TenantRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TenantRepositoryImpl implements TenantRepository {

    private final SpringDataTenantRepository repository;

    public TenantRepositoryImpl(SpringDataTenantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tenant save(Tenant tenant) {
        TenantJpaEntity entity = TenantJpaEntity.fromDomain(tenant);
        TenantJpaEntity saved = repository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<Tenant> findById(UUID id) {
        return repository.findById(id)
                .map(TenantJpaEntity::toDomain);
    }

    @Override
    public Optional<Tenant> findBySlug(String slug) {
        return repository.findBySlug(slug)
                .map(TenantJpaEntity::toDomain);
    }

    @Override
    public boolean existsByDocument(String document) {
        return repository.existsByDocument(document);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return repository.existsBySlug(slug);
    }
}