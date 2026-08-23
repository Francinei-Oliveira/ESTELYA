package com.estelya.pdv.domain;

import java.time.Instant;
import java.util.UUID;

public class CashRegister {

    private final UUID id;
    private final UUID tenantId;
    private final UUID operatorId;
    private CashRegisterStatus status;
    private final Instant openedAt;
    private Instant closedAt;

    public CashRegister(
            UUID id,
            UUID tenantId,
            UUID operatorId,
            CashRegisterStatus status,
            Instant openedAt,
            Instant closedAt) {

        this.id = id;
        this.tenantId = tenantId;
        this.operatorId = operatorId;
        this.status = status;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
    }

    public static CashRegister open(
            UUID tenantId,
            UUID operatorId) {

        return new CashRegister(
                UUID.randomUUID(),
                tenantId,
                operatorId,
                CashRegisterStatus.OPEN,
                Instant.now(),
                null
        );
    }

    public void close() {

        if (status == CashRegisterStatus.CLOSED) {
            throw new IllegalStateException(
                    "O caixa já está fechado."
            );
        }

        status = CashRegisterStatus.CLOSED;
        closedAt = Instant.now();
    }

    public boolean isOpen() {
        return status == CashRegisterStatus.OPEN;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public UUID getOperatorId() {
        return operatorId;
    }

    public CashRegisterStatus getStatus() {
        return status;
    }

    public Instant getOpenedAt() {
        return openedAt;
    }

    public Instant getClosedAt() {
        return closedAt;
    }
}
