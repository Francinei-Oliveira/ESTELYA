package com.estelya.user.application;

import com.estelya.user.domain.User;
import com.estelya.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetUserByIdUseCase {

    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User execute(UUID tenantId, UUID userId) {
        return userRepository.findById(userId)
                .filter(user -> user.getTenantId().equals(tenantId))
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario nao encontrado."));
    }
}