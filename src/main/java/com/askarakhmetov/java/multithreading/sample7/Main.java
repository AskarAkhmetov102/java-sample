package com.askarakhmetov.java.multithreading.sample7;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Пример работы с CompletableFuture
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ". Первый Runnable");
        }).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ". Второй Runnable");
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + ". Третий Runnable");
        }).get();
    }
}
