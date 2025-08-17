package com.askarakhmetov;

public class Sandox {
    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        System.out.println(millis / 1000);
        System.out.println((millis % 1000) * 1000000);

    }
}
