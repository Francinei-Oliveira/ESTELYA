package com.estelya.security;

import com.estelya.auth.application.LoginUseCase;
import com.estelya.user.domain.User;
import com.estelya.user.domain.UserRepository;
import com.estelya.user.domain.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityFoundationTest {

    @Test
    void authenticatesActiveUser() {
        UUID tenantId = UUID.randomUUID();

        User user = User.create(
                tenantId,
                "Owner",
                "owner@estelya.local",
                "hash",
                UserRole.OWNER
        );

        UserRepository repository = mock(UserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);

        when(repository.findByTenantIdAndEmail(
                tenantId, "owner@estelya.local"))
                .thenReturn(Optional.of(user));

        when(encoder.matches("correct", "hash"))
                .thenReturn(true);

        when(jwtService.generateAccessToken(user))
                .thenReturn("jwt-token");

        when(jwtService.getAccessTokenTtlSeconds())
                .thenReturn(900L);

        LoginUseCase useCase =
                new LoginUseCase(repository, encoder, jwtService);

        LoginUseCase.LoginResult result =
                useCase.execute(
                        tenantId,
                        "OWNER@ESTELYA.LOCAL",
                        "correct"
                );

        assertEquals("jwt-token", result.accessToken());
        assertEquals(900L, result.expiresIn());
    }

    @Test
    void rejectsInvalidPassword() {
        UUID tenantId = UUID.randomUUID();

        User user = User.create(
                tenantId,
                "Owner",
                "owner@estelya.local",
                "hash",
                UserRole.OWNER
        );

        UserRepository repository = mock(UserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);

        when(repository.findByTenantIdAndEmail(
                tenantId, "owner@estelya.local"))
                .thenReturn(Optional.of(user));

        when(encoder.matches("wrong", "hash"))
                .thenReturn(false);

        LoginUseCase useCase =
                new LoginUseCase(repository, encoder, jwtService);

        assertThrows(
                BadCredentialsException.class,
                () -> useCase.execute(
                        tenantId,
                        "owner@estelya.local",
                        "wrong"
                )
        );
    }

    @Test
    void enforcesTenantIsolation() {
        UUID tenantId = UUID.randomUUID();
        Instant now = Instant.now();

        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "HS256")
                .subject(UUID.randomUUID().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(900))
                .claim("tenant_id", tenantId.toString())
                .claim("role", "OWNER")
                .build();

        JwtAuthenticationToken authentication =
                new JwtAuthenticationToken(jwt);

        TenantSecurity security = new TenantSecurity();

        assertTrue(
                security.belongsToTenant(
                        authentication, tenantId));

        assertFalse(
                security.belongsToTenant(
                        authentication, UUID.randomUUID()));
    }
}