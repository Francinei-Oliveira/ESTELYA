package com.estelya.tenant.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTenantRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        @Size(max = 200)
        String legalName,

        @NotBlank
        @Size(max = 30)
        String document,

        @NotBlank
        @Size(max = 100)
        String slug
) {
}