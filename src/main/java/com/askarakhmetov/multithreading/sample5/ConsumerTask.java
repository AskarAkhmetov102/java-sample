package com.askarakhmetov.multithreading.sample5;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConsumerTask implements Runnable {

    private final Queue<Message> messages;
    private final Random random = new Random();
    private final Lock lock;
    private final Condition fullCond;
    private final Condition emptyCond;
    private final Condition workUnitDoneCond;

    public ConsumerTask(Queue<Message> messages, Lock lock, Condition fullCond, Condition emptyCond,
                        Condition workUnitDoneCond) {
        this.messages = messages;
        this.lock = lock;
        this.fullCond = fullCond;
        this.emptyCond = emptyCond;
        this.workUnitDoneCond = workUnitDoneCond;
    }

    @Override
    public void run() {
        lock.lock();
        String threadName = Thread.currentThread().getName();
        try {
            while (true) {
                if (!messages.isEmpty()) {
                    var message = messages.poll();
                    //Имитация долгого потребления message
                    Thread.sleep(random.nextLong(1000));
                    System.out.println(threadName + ". Message is consumed. " + message);
                    // Consumer потребил message из очереди. Сообщаем всем потоком Producer, которые ждут на условии fullCond
                    fullCond.signalAll();
                } else {
                    System.out.println(threadName + ". Queue is empty. Consumer is waiting...");
                    // Очередь пустая поэтому ожидаем на условии emptyCond когда какой-нибудь поток Producer добавит
                    // message и вызовет signal
                    emptyCond.await();
                }
                // Consumer отработал и отпустил монитор
                workUnitDoneCond.await(random.nextLong(3) + 1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
