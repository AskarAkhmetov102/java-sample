package com.askarakhmetov.multithreading.sample3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Пример использования методов wait, notify, notifyAll в классической задаче Producer/Consumer
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Queue<Message> queue = new LinkedList<>();
        int QUEUE_LIMIT = 10;
        var producerTask = new ProducerTask(queue, QUEUE_LIMIT);
        var consumerTask = new ConsumerTask(queue);

        Thread producer1 = new Thread(producerTask);
        Thread producer2 = new Thread(producerTask);
        Thread producer3 = new Thread(producerTask);
        Thread producer4 = new Thread(producerTask);
        Thread producer5 = new Thread(producerTask);

        Thread consumer1 = new Thread(consumerTask);
        Thread consumer2 = new Thread(consumerTask);
        Thread consumer3 = new Thread(consumerTask);
        Thread consumer4 = new Thread(consumerTask);
        Thread consumer5 = new Thread(consumerTask);

        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();

        producer1.join();
        producer2.join();
        producer3.join();
        producer4.join();
        producer5.join();

        consumer1.join();
        consumer2.join();
        consumer3.join();
        consumer4.join();
        consumer5.join();
    }
}
