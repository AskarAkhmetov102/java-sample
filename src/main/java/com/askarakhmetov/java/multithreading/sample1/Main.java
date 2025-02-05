package com.askarakhmetov.java.multithreading.sample1;

/**
 * 2 способа создания потоков:
 * <p>
 * <ol>
 *  <li>Наследоваться от класса Thread</li>
 *  <li>Реализовать интерфейс Runnable и передать эту реализацию в конструтор класса Thread</li>
 * </ol>
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Start main");
        var myThread = new MyThread();
        var myRunnable = new Thread(new MyRunnable());

        myThread.start();
        myRunnable.start();

        System.out.println("End main");
    }
}
