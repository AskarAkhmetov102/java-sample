package com.askarakhmetov.java.multithreading.sample4;

public class CounterRunnable implements Runnable {

    private final Counter counter;

    public CounterRunnable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}
