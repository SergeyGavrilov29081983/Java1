package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {

    private static CacheInfo lastFibo;

    public static int fiboNumber(int n) {
        int first;
        int next = 1;
        int current = 0;
        for (int i = 0; i < n; i++) {
            first = next;
            next = current;
            current = first + next;
        }
        return current;
    }

    public static class CacheInfo{
        public int n;
        public int fibo;
    }

    public static CacheInfo getLastFibo() {
        return lastFibo;
    }

    public static void clearLastFibo() {
        lastFibo = null;
    }
}
