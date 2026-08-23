package com.estelya.pdv.domain;

import java.time.Instant;
import java.util.UUID;

public class Sale {

    private final UUID id;
    private final UUID tenantId;
    private final UUID operatorId;

    private SaleStatus status;
    private final Instant createdAt;
    private Instant completedAt;

    public Sale(
            UUID id,
            UUID tenantId,
            UUID operatorId,
            SaleStatus status,
            Instant createdAt,
            Instant completedAt) {

        this.id = id;
        this.tenantId = tenantId;
        this.operatorId = operatorId;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public static Sale create(
            UUID tenantId,
            UUID operatorId) {

        return new Sale(
                UUID.randomUUID(),
                tenantId,
                operatorId,
                SaleStatus.DRAFT,
                Instant.now(),
                null
        );
    }

    public void complete(CashRegister cashRegister) {

        if (cashRegister == null || !cashRegister.isOpen()) {
            throw new IllegalStateException(
                    "Não é possível concluir a venda sem um caixa aberto."
            );
        }

        if (!tenantId.equals(cashRegister.getTenantId())) {
            throw new IllegalStateException(
                    "O caixa informado pertence a outra clínica."
            );
        }

        if (status != SaleStatus.DRAFT) {
            throw new IllegalStateException(
                    "Somente vendas em aberto podem ser concluídas."
            );
        }

        status = SaleStatus.COMPLETED;
        completedAt = Instant.now();
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

    public SaleStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }
}
