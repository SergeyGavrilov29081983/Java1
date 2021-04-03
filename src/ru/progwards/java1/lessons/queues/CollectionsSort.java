package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {

    public static void mySort(Collection<Integer> data) {

        Integer[] a = new Integer[data.size()];
        data.toArray(a);
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int a1 = a[i];
                    a[i] = a[j];
                    a[j] = a1;
                }
            }
        }
        data.clear();
        for (int i = 0; i < a.length; i++) {
            data.add(a[i]);
        }
    }

    public static void minSort(Collection<Integer> data) {
        List<Integer> result = new ArrayList<>();
        while(!data.isEmpty()) {
            int min = Collections.min(data);
            result.add(min);
            data.remove(min);
        }
        data.addAll(result);
    }

    public static void collSort(Collection<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        Collections.sort(result);
        data.clear();
        data.addAll(result);
    }

    public static Collection<String> compareSort() {

       /* в котором сравнить производительность методов и вернуть их имена, отсортированные в порядке производительности,
                первый - самый быстрый.
                В случае равенства производительности каких-то методов, возвращать их названия
        в алфавитном порядке.*/
    return null;
    }

    public static void main(String[] args) {
        CollectionsSort.minSort(Arrays.asList(4, 12, 66, 60, 36, 6, 69, 14));
    }
}
