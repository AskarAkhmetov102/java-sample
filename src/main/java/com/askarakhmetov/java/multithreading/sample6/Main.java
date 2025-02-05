package com.askarakhmetov.java.multithreading.sample6;

import java.util.concurrent.*;

/**
 * Пример работы с ExecutorService
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //Общий пример работы с ExecutorService
        //1. Создаем экземпляр ExecutorService через утилитный класс Executors
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //2. Создаем задачу. Это класс Runnable или Callable
        Runnable runnable = () -> {
            System.out.println("Вызов Runnable");
        };

        Callable<Integer> callable = () -> {
            System.out.println("Вызов Callable");
            return 1;
        };

        //3. Отправляем задачи в ExecutorService
        Future<?> futureRunnable = executorService.submit(runnable);
        Future<Integer> futureCallable = executorService.submit(callable);

        //4. Получаем результат. Метод get - блокирующий. Если нужен неблокирующий способ, то придется использовать polling
        System.out.println("future Runnable: " + futureRunnable.get());
        System.out.println("future Callable: " + futureCallable.get());

        //5. Завершаем работу ExecutorService
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("End");
    }


}
