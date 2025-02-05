package com.askarakhmetov.java.multithreading.sample2;

/**
 * Пример использования synchronized
 * <br/>
 * Есть общий (разделяемый) ресурс для всех потоков (счетчик Counter), доступ к которому нужно синхронизировать.
 * Для этого метод increment в классе Counter объявлен с модификатором synchronized.
 * Без synchronized итоговое значение счетчика может быть любым при каждом запуске,
 * с synchronized значение всегда одно, так как доступ к ресурсу синхронизирован и только
 * один поток в один момент времени может увеличивать счетчик.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var counter = new Counter();

        var thread1 = new Thread(new CounterRunnable(counter));
        var thread2 = new Thread(new CounterRunnable(counter));
        var thread3 = new Thread(new CounterRunnable(counter));
        var thread4 = new Thread(new CounterRunnable(counter));
        var thread5 = new Thread(new CounterRunnable(counter));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println("Count : " + counter.getCount());
    }
}
