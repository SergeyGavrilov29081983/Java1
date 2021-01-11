package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {

    private static CacheInfo lastFibo;

    public static int fiboNumber(int n) {
        return 0;
    }

    public static CacheInfo getLastFibo() {

        return lastFibo;
    }

    public static void clearLastFibo() {

        lastFibo = null;
    }

    public static class CacheInfo {

        public int n;
        public int fibo;

    }
}
