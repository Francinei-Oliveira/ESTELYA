package com.estelya.pdv.application;

import com.estelya.pdv.domain.Sale;
import com.estelya.pdv.domain.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateSaleUseCase {

    private final SaleRepository saleRepository;

    public CreateSaleUseCase(
            SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional
    public Sale execute(
            UUID tenantId,
            UUID operatorId) {

        Sale sale =
                Sale.create(tenantId, operatorId);

        return saleRepository.save(sale);
    }
}