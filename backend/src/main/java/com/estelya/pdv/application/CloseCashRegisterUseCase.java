package com.estelya.pdv.application;

import com.estelya.pdv.domain.CashRegister;
import com.estelya.pdv.domain.CashRegisterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CloseCashRegisterUseCase {

    private final CashRegisterRepository cashRegisterRepository;

    public CloseCashRegisterUseCase(
            CashRegisterRepository cashRegisterRepository) {
        this.cashRegisterRepository = cashRegisterRepository;
    }

    @Transactional
    public CashRegister execute(UUID cashRegisterId) {

        CashRegister cashRegister =
                cashRegisterRepository.findById(cashRegisterId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Caixa não encontrado."
                                )
                        );

        cashRegister.close();

        return cashRegisterRepository.save(cashRegister);
    }
}