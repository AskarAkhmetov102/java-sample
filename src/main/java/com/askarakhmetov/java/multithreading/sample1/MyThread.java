package com.askarakhmetov.java.multithreading.sample1;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread class. Thread name: " + getName());
    }
}
