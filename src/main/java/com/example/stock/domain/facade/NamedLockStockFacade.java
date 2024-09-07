package com.example.stock.domain.facade;

import com.example.stock.domain.repository.LockRepository;
import com.example.stock.domain.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NamedLockStockFacade {

    private final LockRepository lockRepository;
    private final StockService stockService;

//    public void decreaseStock(Long id, Long quantity) {
//        try {
//            lockRepository.getLock(id.toString());
//            stockService.decreaseStock(id, quantity);
//        } finally {
//            lockRepository.releaseLock(id.toString());
//        }
//
//    }
}
