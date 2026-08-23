package com.estelya.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("tenantSecurity")
public class TenantSecurity {

    public boolean belongsToTenant(
            Authentication authentication,
            UUID tenantId) {

        if (authentication == null ||
                !(authentication.getPrincipal() instanceof Jwt jwt)) {
            return false;
        }

        String tokenTenantId =
                jwt.getClaimAsString("tenant_id");

        return tenantId != null &&
                tenantId.toString().equals(tokenTenantId);
    }
}