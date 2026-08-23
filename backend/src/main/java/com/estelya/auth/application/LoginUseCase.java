package com.estelya.auth.application;

import com.estelya.security.JwtService;
import com.estelya.user.domain.User;
import com.estelya.user.domain.UserRepository;
import com.estelya.user.domain.UserStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.UUID;

@Service
public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public LoginResult execute(
            UUID tenantId,
            String email,
            String rawPassword) {

        String normalizedEmail =
                email.trim().toLowerCase(Locale.ROOT);

        User user = userRepository
                .findByTenantIdAndEmail(tenantId, normalizedEmail)
                .orElseThrow(() ->
                        new BadCredentialsException(
                                "E-mail ou senha invalidos."));

        if (user.getStatus() != UserStatus.ACTIVE ||
                !passwordEncoder.matches(
                        rawPassword,
                        user.getPasswordHash())) {

            throw new BadCredentialsException(
                    "E-mail ou senha invalidos.");
        }

        return new LoginResult(
                jwtService.generateAccessToken(user),
                jwtService.getAccessTokenTtlSeconds(),
                user
        );
    }

    public record LoginResult(
            String accessToken,
            long expiresIn,
            User user
    ) {
    }
}