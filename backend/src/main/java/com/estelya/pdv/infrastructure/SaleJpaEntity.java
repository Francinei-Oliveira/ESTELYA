package com.estelya.pdv.infrastructure;

import com.estelya.pdv.domain.Sale;
import com.estelya.pdv.domain.SaleStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "sales")
public class SaleJpaEntity {

    @Id
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @Column(name = "operator_id", nullable = false)
    private UUID operatorId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private SaleStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "completed_at")
    private Instant completedAt;

    protected SaleJpaEntity() {}

    private SaleJpaEntity(
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

    public static SaleJpaEntity fromDomain(Sale sale) {
        return new SaleJpaEntity(
                sale.getId(),
                sale.getTenantId(),
                sale.getOperatorId(),
                sale.getStatus(),
                sale.getCreatedAt(),
                sale.getCompletedAt()
        );
    }

    public Sale toDomain() {
        return new Sale(
                id,
                tenantId,
                operatorId,
                status,
                createdAt,
                completedAt
        );
    }
}