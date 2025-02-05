package com.askarakhmetov.multithreading.sample8;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Long> {
    private static long THRESHOLD = 32000;
    private List<Long> source;
    private int start;
    private int end;
    private long searchedValue;

    public MyRecursiveTask(List<Long> source, int start, int end, long searchedValue) {
        this.source = source;
        this.start = start;
        this.end = end;
        this.searchedValue = searchedValue;
    }

    @Override
    protected Long compute() {
        if (end - start < THRESHOLD) {
            return getCount();
        } else {
            int middle = start + (end - start) / 2;
            MyRecursiveTask task1 = new MyRecursiveTask(source, start, middle, searchedValue);
            MyRecursiveTask task2 = new MyRecursiveTask(source, middle + 1, end, searchedValue);

            task1.fork();
            task2.fork();

            return task2.join() + task1.join();
        }
    }

    public long getCount() {
        long count = 0;
        for (int i = start; i <= end; i++) {
            if (source.get(i) == searchedValue) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "MyRecursiveTask{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
