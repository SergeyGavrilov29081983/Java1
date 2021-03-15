package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Finder {

    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        List<Integer> result = new ArrayList<>(numbers);
        List<Integer> indexes = new ArrayList<>();
        int min = result.get(0) + result.get(1);
        for (int i = 1; i < result.size(); i++) {
            int tmpSum = result.get(i) + result.get(i + 1);
            if (tmpSum < min) {
                tmpSum = min;
            }
            indexes.clear();
            indexes.add(i);
            indexes.add(i + 1);
        }
        return indexes;
    }


    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {
        List<Integer> result = new ArrayList<>(numbers);
        List<Integer> localMax = new ArrayList<>();
        for (int i = 1; i < result.size() - 1; i++) {
            if (result.get(i) > result.get(i + 1) && result.get(i - 1) > result.get(i - 1)) {
                localMax.add(i);
            }
        }
        return localMax;
    }

    public static boolean findSequence(Collection<Integer> numbers) {
        return numbers.containsAll(new ArrayList<>(numbers));
    }

    public static String findSimilar(Collection<String> names) {
        List<String> result = new ArrayList<>(names);
        int counter = 0;
        String name = "";
        for (int i = 0; i < result.size(); i++) {
            name = result.get(i);
            for (String s : result) {
                if (name.equals(s)) {
                    counter = counter + 1;
                } else {
                    break;
                }
            }
        }
        return name + ":" + counter;

    }

   /* найдите максимальное количество повторяющихся подряд элементов.
    Результат вернуть в виде строки <элемент>:<количество>, например Василий:5.
    При равенстве максимального количества у разных повторяющихся элементов,
    вернуть результат для элемента, повторяющаяся последовательность которого началась с наименьшего индекса.*/


    public static void main(String[] args) {
        ArrayList names = new ArrayList();
        names.add("Григорий");
        names.add("Дмитрий");
        names.add("Дмитрий");
        names.add("Александр");
        names.add("Григорий");
        names.add("Григорий");
        System.out.println(findSimilar(names));
    }
}