package com.estelya.user.application;

import com.estelya.tenant.domain.TenantRepository;
import com.estelya.user.domain.User;
import com.estelya.user.domain.UserRepository;
import com.estelya.user.domain.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.UUID;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(
            UserRepository userRepository,
            TenantRepository tenantRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User execute(
            UUID tenantId,
            String name,
            String email,
            String rawPassword,
            UserRole role) {

        tenantRepository.findById(tenantId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Clinica nao encontrada."));

        String normalizedEmail =
                email.trim().toLowerCase(Locale.ROOT);

        if (userRepository.existsByTenantIdAndEmail(
                tenantId, normalizedEmail)) {

            throw new IllegalArgumentException(
                    "Ja existe um usuario com este e-mail nesta clinica.");
        }

        String passwordHash =
                passwordEncoder.encode(rawPassword);

        User user = User.create(
                tenantId,
                name.trim(),
                normalizedEmail,
                passwordHash,
                role
        );

        return userRepository.save(user);
    }
}