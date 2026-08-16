package com.estelya.tenant.api;

import com.estelya.tenant.application.CreateTenantUseCase;
import com.estelya.tenant.application.GetTenantByIdUseCase;
import com.estelya.tenant.domain.Tenant;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    private final CreateTenantUseCase createTenantUseCase;
    private final GetTenantByIdUseCase getTenantByIdUseCase;

    public TenantController(
            CreateTenantUseCase createTenantUseCase,
            GetTenantByIdUseCase getTenantByIdUseCase) {

        this.createTenantUseCase = createTenantUseCase;
        this.getTenantByIdUseCase = getTenantByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<TenantResponse> create(
            @Valid @RequestBody CreateTenantRequest request) {

        Tenant tenant = createTenantUseCase.execute(
                request.name(),
                request.legalName(),
                request.document(),
                request.slug()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TenantResponse.fromDomain(tenant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponse> findById(
            @PathVariable UUID id) {

        Tenant tenant = getTenantByIdUseCase.execute(id);

        return ResponseEntity.ok(
                TenantResponse.fromDomain(tenant)
        );
    }
}