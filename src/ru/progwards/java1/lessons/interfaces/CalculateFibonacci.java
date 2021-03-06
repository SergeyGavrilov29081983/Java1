package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {

    private static CacheInfo lastFibo;

    public static int fiboNumber(int n) {
        lastFibo = new CacheInfo();
        lastFibo.n = n;
        if (lastFibo.fibo == n) return lastFibo.fibo;
        int first;
        int next = 1;
        int current = 0;
        for (int i = 0; i < lastFibo.n; i++) {
            first = next;
            next = current;
            current = first + next;
        }
        lastFibo.fibo = current;
        return current;
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

    public static void main(String[] args) {
        lastFibo = new CacheInfo();
        lastFibo.fibo = 1;
        clearLastFibo();
        System.out.println(getLastFibo());
    }
}
