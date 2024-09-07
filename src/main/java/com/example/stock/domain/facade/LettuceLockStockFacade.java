package com.example.stock.domain.facade;

import com.example.stock.domain.repository.RedisLockRepository;
import com.example.stock.domain.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LettuceLockStockFacade {
    private final RedisLockRepository redisLockRepository;
    private final StockService stockService;

    public void decreaseStock(Long id, Long quantity) throws InterruptedException {
        while (!redisLockRepository.lock(id)) {
            Thread.sleep(100);
        }

        try {
            stockService.decreaseStock(id, quantity);
        } finally {
            redisLockRepository.unlock(id);
        }
    }
}
