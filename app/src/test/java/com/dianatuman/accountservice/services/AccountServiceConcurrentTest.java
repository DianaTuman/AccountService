package com.dianatuman.accountservice.services;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static com.dianatuman.accountservice.services.Utils.randomId;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceConcurrentTest {
    private final AccountService accountService = new AccountServiceImpl();

    @Test
    public void shouldHandleConcurrentDeposits() throws InterruptedException {
        // given
        List<Integer> ids = new Random().ints().limit(100).boxed().collect(toList());
        ExecutorService service = Executors.newFixedThreadPool(4);
        CountDownLatch start = new CountDownLatch(1);

        // when
        ids.forEach(id ->
                service.submit(() -> {
                    try {
                        start.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    accountService.addAmount(id, 500L);
                }));
        start.countDown();
        service.awaitTermination(10, SECONDS);

        // then
        ids.forEach(id -> assertEquals(500L, accountService.getAmount(id)));
    }

    @Test
    public void shouldHandleConcurrentDepositsToOneAccount() throws InterruptedException {
        // given
        var id = randomId();
        ExecutorService service = Executors.newFixedThreadPool(4);
        CountDownLatch start = new CountDownLatch(1);

        // when
        IntStream.range(0, 100).forEach(i -> {
            service.submit(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                accountService.addAmount(id, 500L);
            });
        });
        start.countDown();
        service.awaitTermination(10, SECONDS);

        // then
        assertEquals(100 * 500L, accountService.getAmount(id));
    }
}
