package com.askarakhmetov.multithreading.sample1;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable class. Thread name: " + Thread.currentThread().getName());
    }
}
