package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {

    public static void mySort(Collection<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        for (int i = 0; i < result.size(); i++) {
            for (int j = i + 1; j < result.size(); j++) {
                if (result.get(i) > result.get(j)) {
                    int a1 = result.get(i);
                    int a2 = result.get(j);
                    result.add(i, a1);
                    result.add(j, a2);
                }
            }
        }
    }

    public static void minSort(Collection<Integer> data) {
        List<Integer> list = new ArrayList<>(data);
        List<Integer> result = new ArrayList<>(list.size());
        int min;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            min = Collections.min(list);
            result.add(min);
            list.remove((Integer) min);
        }
        list = new ArrayList<>(data);
        Collections.copy(list, result);



    }


    public static void collSort(Collection<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        Collections.sort(result);
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
