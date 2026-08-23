package com.estelya.pdv.domain;

import java.util.Optional;
import java.util.UUID;

public interface SaleRepository {

    Sale save(Sale sale);

    Optional<Sale> findById(UUID id);
}
