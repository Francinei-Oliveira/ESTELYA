package com.estelya.pdv.api;

import com.estelya.pdv.domain.CashRegister;
import com.estelya.pdv.domain.CashRegisterStatus;

import java.time.Instant;
import java.util.UUID;

public record CashRegisterResponse(
        UUID id,
        UUID tenantId,
        UUID operatorId,
        CashRegisterStatus status,
        Instant openedAt,
        Instant closedAt
) {

    public static CashRegisterResponse fromDomain(
            CashRegister cashRegister) {

        return new CashRegisterResponse(
                cashRegister.getId(),
                cashRegister.getTenantId(),
                cashRegister.getOperatorId(),
                cashRegister.getStatus(),
                cashRegister.getOpenedAt(),
                cashRegister.getClosedAt()
        );
    }
}