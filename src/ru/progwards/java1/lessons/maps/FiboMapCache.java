package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;



   /*                 Задача 1, класс FiboMapCache: не пройдено, оценка: 0.0
        Комментарий:
        ERROR: Тест "Тест для cacheOn = false" не пройден. Во время выполнения возникло исключение java.lang.NullPointerException
        ru.progwards.java1.lessons.maps.FiboMapCache.fiboNumber(FiboMapCache.java:22)
        ERROR: Тест "Тест для cacheOn = true" не пройден. Похоже, что вызовы метода fiboNumber для последовальных
        значений параметра n возвращают неверную последовательность чисел Фибоначчи:
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29...
        Ожидалось:
        1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025,121393,196418,317811,514229
        По данной задаче в целом не зачет, решение возвращено на доработку. Задача выполнена на 0.00%%*/

public class FiboMapCache {

     private Map<Integer, BigDecimal> fiboCache;
     private boolean isCacheOn;

    public FiboMapCache(boolean cacheOn) {
        isCacheOn = cacheOn;
        if (cacheOn) {
            fiboCache = new TreeMap<>();
        }
    }

    public BigDecimal fiboNumber(int n) {

        if(isCacheOn) {
            if (fiboCache.containsKey(n)) {
                return fiboCache.get(n);
            }
            int first;
            int next = 1;
            int current = 0;
            for (int i = 0; i < n; i++) {
                first = next;
                next = current;
                current = first + next;

            }
            BigDecimal result  = new BigDecimal(current);
            if (!fiboCache.containsKey(n)) {
                return fiboCache.put(n, result);
            }
            return result;
        }

        int first;
        int next = 1;
        int current = 0;
        for (int i = 0; i < n; i++) {
            first = next;
            next = current;
            current = first + next;
        }
        return new BigDecimal(current);
    }

    public void clearCahe() {
        fiboCache = null;
    }

    public static void test() {
        FiboMapCache fiboMapCache = new FiboMapCache(true);
        long start = System.currentTimeMillis();

        for (int i = 0 ; i <=1000 ; i++) {
            fiboMapCache.fiboNumber(i);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("fiboNumber cacheOn=" + fiboMapCache.isCacheOn +  "время выполнения "  + time);

        FiboMapCache fiboMapCache1 = new FiboMapCache(false);
        long start1 = System.currentTimeMillis();

        for (int i = 0 ; i <=1000 ; i++) {
            fiboMapCache1.fiboNumber(i);
        }
        long time1 = System.currentTimeMillis() - start;
        System.out.println("fiboNumber cacheOn=" + fiboMapCache1.isCacheOn +  "время выполнения "  + time1);
    }
}

