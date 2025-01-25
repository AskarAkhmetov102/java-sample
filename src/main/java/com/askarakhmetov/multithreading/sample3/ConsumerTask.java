package com.askarakhmetov.multithreading.sample3;

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
            while (true) {
                if (!messages.isEmpty()) {
                    var message = messages.poll();
                    System.out.println("Message is consumed. " + message);
                } else {
                    System.out.println("Queue is empty. Consumer is waiting...");
                }
                // сообщаем всем ждущим потокам Producer, что они могут продолжить свою работу после того
                // как поток Consumer отпустит монитор messages
                messages.notifyAll();
                try {
                    // поток Consumer отработал, отпустил монитор messages и теперь переходит в состояние ожидания
                    messages.wait((random.nextLong(5) + 1) * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
