package com.example.stock.domain.service;

import com.example.stock.domain.Stock;
import com.example.stock.domain.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    /*
    1. 일반적인 방법 - 동시성 문제 발생

    @Transactional
    public void decreaseStock(Long id, Long quantity) {
        stockRepository.findById(id).ifPresent(stock -> {
            stock.decrementQuantity(quantity);
            stockRepository.saveAndFlush(stock);
        });
    }
    */

    /*
    2. 동시성 문제 해결 1 - 자바의 synchronized 이용
    문제점 - 하나의 서버일 경우에는 문제가 없지만 서버가 여러개일 경우,
    다수의 서버에서 하나의 데이를 수정할때 동시성 문제가 발생.

    public synchronized void decreaseStock(Long id, Long quantity) {
        stockRepository.findById(id).ifPresent(stock -> {
            stock.decrementQuantity(quantity);
            stockRepository.saveAndFlush(stock);
        });
    }
    */

    /*
    3. 동시성 문제 해결 2 - 데이터베이스의 Pessimistic Lock 활용
    데이터베이스 자체에 Lock 걸어서 하나의 데이터에 여러 서버의 요청이 들어와도 하나의 요청만 받아들인다.

    @Transactional
    public void decreaseStock(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        stock.decrementQuantity(quantity);
        stockRepository.saveAndFlush(stock);
    }
    */

    /*
    4. 동시성 문제 해결 3 - 데이터베이스의 OptimisticLock 활용
    엔티티에 버전을 추가서해서 요청이 들어올때마다 DB 내의 데이터 버전과 요청의 버전이 맞는지 검사한다.
    맞지 않다면, 재시도한다. 별도의 Lock 을 걸지 않는다. 하지만, 개발자가 재실행하는 메소드를 만들어줘야 한다.

    @Transactional
    public void decreaseStock(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithOptimisticLock(id);
        stock.decrementQuantity(quantity);
        stockRepository.saveAndFlush(stock);
    */

    /*
    5. 동시성 문제 해결 4 - 데이터베이스의 NamedLock 활용

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decreaseStock(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrementQuantity(quantity);
        stockRepository.saveAndFlush(stock);
    }
    */

    // redis 활용 - Lettuce, Redisson
    @Transactional
    public void decreaseStock(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrementQuantity(quantity);
        stockRepository.saveAndFlush(stock);
    }
}


