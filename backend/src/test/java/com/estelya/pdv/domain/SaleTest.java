package com.estelya.pdv.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    @Test
    void shouldCompleteSaleWhenCashRegisterIsOpen() {

        UUID tenantId = UUID.randomUUID();
        UUID operatorId = UUID.randomUUID();

        CashRegister cashRegister =
                CashRegister.open(tenantId, operatorId);

        Sale sale =
                Sale.create(tenantId, operatorId);

        sale.complete(cashRegister);

        assertEquals(SaleStatus.COMPLETED, sale.getStatus());
        assertNotNull(sale.getCompletedAt());
    }

    @Test
    void shouldNotCompleteSaleWhenCashRegisterIsClosed() {

        UUID tenantId = UUID.randomUUID();
        UUID operatorId = UUID.randomUUID();

        CashRegister cashRegister =
                CashRegister.open(tenantId, operatorId);

        cashRegister.close();

        Sale sale =
                Sale.create(tenantId, operatorId);

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> sale.complete(cashRegister)
                );

        assertEquals(
                "Não é possível concluir a venda sem um caixa aberto.",
                exception.getMessage()
        );
    }

    @Test
    void shouldNotUseCashRegisterFromAnotherTenant() {

        UUID tenantA = UUID.randomUUID();
        UUID tenantB = UUID.randomUUID();
        UUID operatorId = UUID.randomUUID();

        CashRegister cashRegister =
                CashRegister.open(tenantA, operatorId);

        Sale sale =
                Sale.create(tenantB, operatorId);

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> sale.complete(cashRegister)
                );

        assertEquals(
                "O caixa informado pertence a outra clínica.",
                exception.getMessage()
        );
    }
}
