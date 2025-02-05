package com.askarakhmetov.java.multithreading.sample8;


import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * Пример работы с ForkJoinPool
 * Задача: посчитать, сколько раз определенное число встречается в списке из 1 млн. элементов
 * В списке содержатся случайные числа от 1 до 100
 */
public class Main {
    public static void main(String[] args) {
        int sourceSize = 1_000_000;
        long searchedValue = 42;
        Random r = new Random();
        List<Long> source = LongStream.generate(() -> r.nextLong(100) + 1).limit(sourceSize).boxed().toList();
        System.out.println("Source size: " + source.size());

        runWithForkJoinPool(source, searchedValue);
        runInForLoop(source, searchedValue);
    }

    public static void runWithForkJoinPool(List<Long> source, long searchedValue) {
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        MyRecursiveTask task = new MyRecursiveTask(source, 0, source.size() - 1, searchedValue);
        long count = forkJoinPool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Result in ForkJoinPool: " + count + ". Time: " + (endTime - startTime) + " ms");
    }

    public static void runInForLoop(List<Long> source, long searchedValue) {
        long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < source.size(); i++) {
            if (source.get(i) == searchedValue) {
                count++;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Result in for loop: " + count + ". Time: " + (endTime - startTime) + " ms");
    }
}
