package com.dianatuman.accountservice.services;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Utils {

    public static int randomId() {
        return new Random().nextInt();
    }

    public static void runConcurrently(Integer numberOfRuns, Consumer<Integer> task) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        CountDownLatch start = new CountDownLatch(1);

        IntStream.range(0, numberOfRuns).forEach(i -> {
            service.submit(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                task.accept(i);
            });
        });
        try {
            service.awaitTermination(10, SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        start.countDown();
    }

}
