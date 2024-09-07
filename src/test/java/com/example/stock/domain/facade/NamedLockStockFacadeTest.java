package com.example.stock.domain.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.stock.domain.Stock;
import com.example.stock.domain.repository.StockRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NamedLockStockFacadeTest {


    @Autowired
    private NamedLockStockFacade namedLockStockFacade;

    @Autowired
    private StockRepository stockRepository;

    Long id;

    @BeforeEach
    void setUp() {
        Stock stock = new Stock(1L, 100L);
        stockRepository.saveAndFlush(stock);
        id = stock.getId();
    }

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
    }
/*

    @Test
    public void 동시에_100개_요청() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    namedLockStockFacade.decreaseStock(id, 1L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertEquals(0, stockRepository.findById(id).orElseThrow().getQuantity());
    }
*/

}