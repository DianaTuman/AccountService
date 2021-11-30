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

}
