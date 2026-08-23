package com.estelya.pdv.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataSaleRepository
        extends JpaRepository<SaleJpaEntity, UUID> {
}