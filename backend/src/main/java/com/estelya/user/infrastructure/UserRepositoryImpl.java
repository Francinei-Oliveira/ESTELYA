package com.estelya.user.infrastructure;

import com.estelya.user.domain.User;
import com.estelya.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SpringDataUserRepository repository;

    public UserRepositoryImpl(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = UserJpaEntity.fromDomain(user);
        return repository.save(entity).toDomain();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id)
                .map(UserJpaEntity::toDomain);
    }

    @Override
    public Optional<User> findByTenantIdAndEmail(
            UUID tenantId,
            String email) {

        return repository
                .findByTenantIdAndEmailIgnoreCase(tenantId, email)
                .map(UserJpaEntity::toDomain);
    }

    @Override
    public boolean existsByTenantIdAndEmail(
            UUID tenantId,
            String email) {

        return repository
                .existsByTenantIdAndEmailIgnoreCase(tenantId, email);
    }
}