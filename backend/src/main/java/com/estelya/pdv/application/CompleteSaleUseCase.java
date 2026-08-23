package com.estelya.pdv.application;

import com.estelya.pdv.domain.CashRegister;
import com.estelya.pdv.domain.CashRegisterRepository;
import com.estelya.pdv.domain.Sale;
import com.estelya.pdv.domain.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CompleteSaleUseCase {

    private final SaleRepository saleRepository;
    private final CashRegisterRepository cashRegisterRepository;

    public CompleteSaleUseCase(
            SaleRepository saleRepository,
            CashRegisterRepository cashRegisterRepository) {
        this.saleRepository = saleRepository;
        this.cashRegisterRepository = cashRegisterRepository;
    }

    @Transactional
    public Sale execute(
            UUID saleId,
            UUID cashRegisterId) {

        Sale sale =
                saleRepository.findById(saleId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Venda não encontrada."
                                )
                        );

        CashRegister cashRegister =
                cashRegisterRepository.findById(cashRegisterId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Caixa não encontrado."
                                )
                        );

        sale.complete(cashRegister);

        return saleRepository.save(sale);
    }
}