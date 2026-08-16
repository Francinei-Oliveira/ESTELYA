package com.estelya.tenant.application;

import com.estelya.tenant.domain.Tenant;
import com.estelya.tenant.domain.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTenantUseCase {

    private final TenantRepository tenantRepository;

    public CreateTenantUseCase(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public Tenant execute(
            String name,
            String legalName,
            String document,
            String slug) {

        if (tenantRepository.existsByDocument(document)) {
            throw new IllegalArgumentException(
                    "Já existe uma clínica cadastrada com este documento."
            );
        }

        if (tenantRepository.existsBySlug(slug)) {
            throw new IllegalArgumentException(
                    "Já existe uma clínica cadastrada com este slug."
            );
        }

        Tenant tenant = Tenant.create(
                name,
                legalName,
                document,
                slug
        );

        return tenantRepository.save(tenant);
    }
}