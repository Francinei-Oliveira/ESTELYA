package com.estelya.pdv.infrastructure;

import com.estelya.pdv.domain.CashRegister;
import com.estelya.pdv.domain.CashRegisterRepository;
import com.estelya.pdv.domain.CashRegisterStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CashRegisterRepositoryImpl implements CashRegisterRepository {

    private final SpringDataCashRegisterRepository repository;

    public CashRegisterRepositoryImpl(
            SpringDataCashRegisterRepository repository) {
        this.repository = repository;
    }

    @Override
    public CashRegister save(CashRegister cashRegister) {
        return repository.save(
                CashRegisterJpaEntity.fromDomain(cashRegister)
        ).toDomain();
    }

    @Override
    public Optional<CashRegister> findById(UUID id) {
        return repository.findById(id)
                .map(CashRegisterJpaEntity::toDomain);
    }

    @Override
    public Optional<CashRegister> findOpenByTenantId(UUID tenantId) {
        return repository
                .findFirstByTenantIdAndStatus(
                        tenantId,
                        CashRegisterStatus.OPEN
                )
                .map(CashRegisterJpaEntity::toDomain);
    }
}