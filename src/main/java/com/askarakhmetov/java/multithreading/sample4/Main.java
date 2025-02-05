package com.askarakhmetov.java.multithreading.sample4;

/**
 * Пример использования класса Atomic*
 * <br/>
 * Много потоков одновременно обновляют счётчик. Внутри Atomic* есть volatile-переменная и экземпляр класса Unsafe.
 * Обновление этой переменной происходит через операцию compare-and-swap (compare-and-set)
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var counter = new Counter();

        var thread1 = new Thread(new CounterRunnable(counter));
        var thread2 = new Thread(new CounterRunnable(counter));
        var thread3 = new Thread(new CounterRunnable(counter));
        var thread4 = new Thread(new CounterRunnable(counter));
        var thread5 = new Thread(new CounterRunnable(counter));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println("Count : " + counter.getCount());
    }
}
