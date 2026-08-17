package com.estelya.tenant.application;

import com.estelya.tenant.domain.Tenant;
import com.estelya.tenant.domain.TenantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Clínica não encontrada."
                        )
                );
    }
}
