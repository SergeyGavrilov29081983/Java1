package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class FiboMapCache {

    private Map<Integer, BigDecimal> fiboCache;
    private boolean isCacheOn;

    public FiboMapCache(boolean cacheOn) {
        isCacheOn = cacheOn;
        if (cacheOn) {
            fiboCache = new TreeMap<>();
        }
    }

    public static void test() {
        FiboMapCache fiboMapCache = new FiboMapCache(true);
        long start = System.currentTimeMillis();

        for (int i = 0; i <= 1000; i++) {
            fiboMapCache.fiboNumber(i);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("fiboNumber cacheOn= " + fiboMapCache.isCacheOn + " время выполнения " + time);

        FiboMapCache fiboMapCache1 = new FiboMapCache(false);
        long start1 = System.currentTimeMillis();

        for (int i = 0; i <= 1000; i++) {
            fiboMapCache1.fiboNumber(i);
        }
        long time1 = System.currentTimeMillis() - start;
        System.out.println("fiboNumber cacheOn= " + fiboMapCache1.isCacheOn + " время выполнения " + time1);
    }

    public BigDecimal fiboNumber(int n) {

        if (isCacheOn) {
            if (fiboCache==null) {
                fiboCache = new HashMap<>();
            }
            if (fiboCache.containsKey(n)) {
                return fiboCache.get(n);
            }
            int current = calculateFiboNumber(n);
            BigDecimal result = new BigDecimal(current);
            fiboCache.put(n, result);
            return result;
        }

        int current = calculateFiboNumber(n);
        return new BigDecimal(current);
    }

    private int calculateFiboNumber(int n) {
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

    public void clearCahe() {
        fiboCache = null;
    }

    public static void main(String[] args) {
        test();
    }
}

