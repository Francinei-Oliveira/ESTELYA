package com.estelya.pdv.infrastructure;

import com.estelya.pdv.domain.CashRegisterStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataCashRegisterRepository
        extends JpaRepository<CashRegisterJpaEntity, UUID> {

    Optional<CashRegisterJpaEntity>
        findFirstByTenantIdAndStatus(UUID tenantId, CashRegisterStatus status);
}