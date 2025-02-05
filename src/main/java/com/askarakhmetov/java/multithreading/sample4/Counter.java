package com.askarakhmetov.java.multithreading.sample4;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger count;

    public Counter() {
        count = new AtomicInteger();
    }

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
