package com.estelya.pdv.application;

import com.estelya.pdv.domain.CashRegister;
import com.estelya.pdv.domain.CashRegisterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OpenCashRegisterUseCase {

    private final CashRegisterRepository cashRegisterRepository;

    public OpenCashRegisterUseCase(
            CashRegisterRepository cashRegisterRepository) {
        this.cashRegisterRepository = cashRegisterRepository;
    }

    @Transactional
    public CashRegister execute(
            UUID tenantId,
            UUID operatorId) {

        cashRegisterRepository.findOpenByTenantId(tenantId)
                .ifPresent(existing -> {
                    throw new IllegalStateException(
                            "Já existe um caixa aberto para esta clínica."
                    );
                });

        CashRegister cashRegister =
                CashRegister.open(tenantId, operatorId);

        return cashRegisterRepository.save(cashRegister);
    }
}