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
            BigDecimal result = calculateFiboNumber(n);
            fiboCache.put(n,result);
            return result;
        } else {
            return calculateFiboNumber(n);
        }
    }

    private BigDecimal calculateFiboNumber(int n) {
        if (n==1 || n==2) {
            return BigDecimal.ONE;
        }
        BigDecimal fNum = BigDecimal.ZERO;
        BigDecimal fNum1 = BigDecimal.ONE;
        BigDecimal fNum2 = BigDecimal.ONE;
        for (int i = 3; i<=n; i++) {
            fNum = fNum1.add(fNum2);
            fNum1 = fNum2;
            fNum2 = fNum;
        }
        return fNum;
    }

    public void clearCahe() {
        fiboCache = null;
    }

    public static void main(String[] args) {
        test();
    }
}

