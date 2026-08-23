package com.estelya.pdv.infrastructure;

import com.estelya.pdv.domain.CashRegister;
import com.estelya.pdv.domain.CashRegisterStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cash_registers")
public class CashRegisterJpaEntity {

    @Id
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "operator_id", nullable = false)
    private UUID operatorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CashRegisterStatus status;

    @Column(name = "opened_at", nullable = false)
    private Instant openedAt;

    @Column(name = "closed_at")
    private Instant closedAt;

    protected CashRegisterJpaEntity() {}

    private CashRegisterJpaEntity(
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

    public static CashRegisterJpaEntity fromDomain(CashRegister cashRegister) {
        return new CashRegisterJpaEntity(
                cashRegister.getId(),
                cashRegister.getTenantId(),
                cashRegister.getOperatorId(),
                cashRegister.getStatus(),
                cashRegister.getOpenedAt(),
                cashRegister.getClosedAt()
        );
    }

    public CashRegister toDomain() {
        return new CashRegister(
                id,
                tenantId,
                operatorId,
                status,
                openedAt,
                closedAt
        );
    }
}