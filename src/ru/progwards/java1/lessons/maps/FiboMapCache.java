package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class FiboMapCache {

      Map<Integer, BigDecimal> fiboCache;

    public FiboMapCache(boolean cacheOn) {
        if (cacheOn) {
            fiboCache = new TreeMap<>();
        }
    }

    public BigDecimal fiboNumber(int n) {
        int first;
        int next = 1;
        int current = 0;
        for (int i = 0; i < n; i++) {
            first = next;
            next = current;
            current = first + next;
        }
        return new BigDecimal(n);
    }


    public void clearCahe() {
        fiboCache = null;
    }
}
