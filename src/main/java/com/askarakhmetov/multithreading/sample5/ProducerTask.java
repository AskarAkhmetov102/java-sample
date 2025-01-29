package com.askarakhmetov.multithreading.sample5;

import com.askarakhmetov.util.Utils;

import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ProducerTask implements Runnable {

    private final Queue<Message> messages;
    private final int queueLimit;
    private final Random random = new Random();
    private final Lock lock;
    private final Condition fullCond;
    private final Condition emptyCond;
    private final Condition workUnitDoneCond;

    public ProducerTask(Queue<Message> messages, int queueLimit, Lock lock, Condition fullCond, Condition emptyCond,
                        Condition workUnitDoneCond) {
        this.messages = messages;
        this.queueLimit = queueLimit;
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
                if (messages.size() < queueLimit) {
                    var message = new Message(UUID.randomUUID(), Utils.generateAlphanumericStr(10));
                    //Имитация долгого создания message
                    Thread.sleep(random.nextLong(1000));
                    messages.offer(message);
                    System.out.println(threadName + ". Message added to queue. " + message);
                    // Producer добавил message в очередь. Сообщаем всем потокам Consumer, которые ждут на условии emptyCond
                    emptyCond.signalAll();
                } else {
                    System.out.println(threadName + ". Queue if full. Producer waiting...");
                    // Очередь полная поэтому ожидаем на условии fullCond когда какой-нибудь поток Consumer потребит
                    // message и вызовет signal
                    fullCond.await();
                }
                // Producer отработал и отпустил монитор
                workUnitDoneCond.await(random.nextLong(3) + 1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
