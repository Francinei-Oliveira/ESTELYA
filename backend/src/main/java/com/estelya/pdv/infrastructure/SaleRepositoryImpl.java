package com.estelya.pdv.infrastructure;

import com.estelya.pdv.domain.Sale;
import com.estelya.pdv.domain.SaleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SaleRepositoryImpl implements SaleRepository {

    private final SpringDataSaleRepository repository;

    public SaleRepositoryImpl(SpringDataSaleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sale save(Sale sale) {
        return repository.save(
                SaleJpaEntity.fromDomain(sale)
        ).toDomain();
    }

    @Override
    public Optional<Sale> findById(UUID id) {
        return repository.findById(id)
                .map(SaleJpaEntity::toDomain);
    }
}