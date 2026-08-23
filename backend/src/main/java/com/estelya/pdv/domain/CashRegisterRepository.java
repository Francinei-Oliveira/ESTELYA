package com.estelya.pdv.domain;

import java.util.Optional;
import java.util.UUID;

public interface CashRegisterRepository {

    CashRegister save(CashRegister cashRegister);

    Optional<CashRegister> findById(UUID id);

    Optional<CashRegister> findOpenByTenantId(UUID tenantId);
}
