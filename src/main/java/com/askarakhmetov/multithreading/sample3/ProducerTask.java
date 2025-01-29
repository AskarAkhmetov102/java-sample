package com.askarakhmetov.multithreading.sample3;

import com.askarakhmetov.util.Utils;

import java.util.Queue;
import java.util.Random;
import java.util.UUID;

public class ProducerTask implements Runnable {

    private final Queue<Message> messages;
    private final int queueLimit;
    private final Random random = new Random();

    public ProducerTask(Queue<Message> messages, int queueLimit) {
        this.messages = messages;
        this.queueLimit = queueLimit;
    }

    @Override
    public void run() {
        synchronized (messages) {
            String threadName = Thread.currentThread().getName();
            while (true) {
                if (messages.size() < queueLimit) {
                    var message = new Message(UUID.randomUUID(), Utils.generateAlphanumericStr(10));
                    //Имитация долгого создания message
                    try {
                        Thread.sleep(random.nextLong(1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    messages.offer(message);
                    System.out.println(threadName + ". Message added to queue. " + message);
                } else {
                    System.out.println(threadName + ". Queue if full. Producer waiting...");
                }
                // сообщаем всем ждущим потокам Consumer, что они могут продолжить свою работу после того
                // как поток Producer отпустит монитор messages
                messages.notifyAll();
                try {
                    // поток Producer отработал, отпустил монитор messages и теперь переходит в состояние ожидания
                    messages.wait((random.nextLong(11) + 1) * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
