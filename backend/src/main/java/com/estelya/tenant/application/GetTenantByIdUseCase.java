package com.estelya.tenant.application;

import com.estelya.tenant.domain.Tenant;
import com.estelya.tenant.domain.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetTenantByIdUseCase {

    private final TenantRepository tenantRepository;

    public GetTenantByIdUseCase(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Transactional(readOnly = true)
    public Tenant execute(UUID id) {
        return tenantRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Clínica não encontrada.")
                );
    }
}