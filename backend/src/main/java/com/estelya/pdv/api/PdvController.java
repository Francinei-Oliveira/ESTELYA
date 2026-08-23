package com.estelya.pdv.api;

import com.estelya.pdv.application.CloseCashRegisterUseCase;
import com.estelya.pdv.application.CompleteSaleUseCase;
import com.estelya.pdv.application.CreateSaleUseCase;
import com.estelya.pdv.application.OpenCashRegisterUseCase;
import com.estelya.pdv.domain.CashRegister;
import com.estelya.pdv.domain.Sale;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pdv")
public class PdvController {

    private final OpenCashRegisterUseCase openCashRegisterUseCase;
    private final CloseCashRegisterUseCase closeCashRegisterUseCase;
    private final CreateSaleUseCase createSaleUseCase;
    private final CompleteSaleUseCase completeSaleUseCase;

    public PdvController(
            OpenCashRegisterUseCase openCashRegisterUseCase,
            CloseCashRegisterUseCase closeCashRegisterUseCase,
            CreateSaleUseCase createSaleUseCase,
            CompleteSaleUseCase completeSaleUseCase) {

        this.openCashRegisterUseCase = openCashRegisterUseCase;
        this.closeCashRegisterUseCase = closeCashRegisterUseCase;
        this.createSaleUseCase = createSaleUseCase;
        this.completeSaleUseCase = completeSaleUseCase;
    }

    @PostMapping("/cash-registers/open")
    public ResponseEntity<CashRegisterResponse> openCashRegister(
            @Valid @RequestBody OpenCashRegisterRequest request) {

        CashRegister cashRegister =
                openCashRegisterUseCase.execute(
                        request.tenantId(),
                        request.operatorId()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CashRegisterResponse.fromDomain(cashRegister));
    }

    @PostMapping("/cash-registers/{id}/close")
    public ResponseEntity<CashRegisterResponse> closeCashRegister(
            @PathVariable UUID id) {

        CashRegister cashRegister =
                closeCashRegisterUseCase.execute(id);

        return ResponseEntity.ok(
                CashRegisterResponse.fromDomain(cashRegister)
        );
    }

    @PostMapping("/sales")
    public ResponseEntity<SaleResponse> createSale(
            @Valid @RequestBody CreateSaleRequest request) {

        Sale sale =
                createSaleUseCase.execute(
                        request.tenantId(),
                        request.operatorId()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SaleResponse.fromDomain(sale));
    }

    @PostMapping("/sales/{saleId}/complete/{cashRegisterId}")
    public ResponseEntity<SaleResponse> completeSale(
            @PathVariable UUID saleId,
            @PathVariable UUID cashRegisterId) {

        Sale sale =
                completeSaleUseCase.execute(
                        saleId,
                        cashRegisterId
                );

        return ResponseEntity.ok(
                SaleResponse.fromDomain(sale)
        );
    }
}