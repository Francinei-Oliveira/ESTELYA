package com.estelya.pdv.api;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateSaleRequest(

        @NotNull
        UUID tenantId,

        @NotNull
        UUID operatorId
) {
}