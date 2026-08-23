package com.estelya.pdv.api;

import com.estelya.pdv.domain.Sale;
import com.estelya.pdv.domain.SaleStatus;

import java.time.Instant;
import java.util.UUID;

public record SaleResponse(
        UUID id,
        UUID tenantId,
        UUID operatorId,
        SaleStatus status,
        Instant createdAt,
        Instant completedAt
) {

    public static SaleResponse fromDomain(Sale sale) {

        return new SaleResponse(
                sale.getId(),
                sale.getTenantId(),
                sale.getOperatorId(),
                sale.getStatus(),
                sale.getCreatedAt(),
                sale.getCompletedAt()
        );
    }
}