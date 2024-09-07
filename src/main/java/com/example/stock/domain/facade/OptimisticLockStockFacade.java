package com.example.stock.domain.facade;

import com.example.stock.domain.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptimisticLockStockFacade {
    private final StockService stockService;

    /*
    public void decreaseStock(Long id, Long quantity) throws InterruptedException {
        while (true) {
            try {
                stockService.decreaseStock(id, quantity);
                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }
    */
}
