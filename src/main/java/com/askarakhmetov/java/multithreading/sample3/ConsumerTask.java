package com.askarakhmetov.java.multithreading.sample3;

import java.util.Queue;
import java.util.Random;

public class ConsumerTask implements Runnable {

    private final Queue<Message> messages;
    private final Random random = new Random();

    public ConsumerTask(Queue<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        synchronized (messages) {
            String threadName = Thread.currentThread().getName();
            while (true) {
                if (!messages.isEmpty()) {
                    var message = messages.poll();
                    try {
                        //Имитация долгого потребления message
                        Thread.sleep(random.nextLong(1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(threadName + ". Message is consumed. " + message);
                } else {
                    System.out.println(threadName + ". Queue is empty. Consumer is waiting...");
                }
                // сообщаем всем ждущим потокам Producer, что они могут продолжить свою работу после того
                // как поток Consumer отпустит монитор messages
                messages.notifyAll();
                try {
                    // поток Consumer отработал, отпустил монитор messages и теперь переходит в состояние ожидания
                    messages.wait((random.nextLong(11) + 1) * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
