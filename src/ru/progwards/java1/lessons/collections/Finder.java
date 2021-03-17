package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {

    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        List<Integer> result = new ArrayList<>(numbers);
        List<Integer> indexes = new ArrayList<>();
        int min = result.get(0) + result.get(1);
        for (int i = 1; i < result.size() - 1; i++) {
            if (result.get(i) + result.get(i + 1) < min) {
                min = result.get(i) + result.get(i + 1);
                indexes.clear();
                indexes.add(i);
                indexes.add(i + 1);
            }
        }
        return indexes;
    }


    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {
        List<Integer> result = new ArrayList<>(numbers);
        List<Integer> localMax = new ArrayList<>();
        for (int i = 1; i < result.size() - 1; i++) {
            if (result.get(i) > result.get(i - 1) && result.get(i) > result.get(i + 1)) {
                localMax.add(result.get(i));
            }


        }
        return localMax;
    }

    public static boolean findSequence(Collection<Integer> numbers) {
        for (int i = 1; i <= numbers.size(); i++) {
            if (!numbers.contains(i)) {
                return false;
            }
        }
        return true;

    }

    public static String findSimilar(Collection<String> names) {
        List<String> result = new ArrayList<>(names);
        int counter = 0;
        String name = "";
        for (int i = 0; i < result.size(); i++) {
            name = result.get(i);
            for (int i1 = 1; i1 < result.size(); i1++) {
                String s = result.get(i1);
                if (name.equals(s)) {
                    counter += 1;
                } else {
                    break;
                }
            }

        }

        return result.get(Collections.frequency(names, name)) + ":" + counter;
    }

   /* найдите максимальное количество повторяющихся подряд элементов.
    Результат вернуть в виде строки <элемент>:<количество>, например Василий:5.
    При равенстве максимального количества у разных повторяющихся элементов,
    вернуть результат для элемента, повторяющаяся последовательность которого началась с наименьшего индекса.*/


    public static void main(String[] args) {
        ArrayList names = new ArrayList();

        names.add("Александр");
        names.add("Александр");
        names.add("Григорий");
        names.add("Григорий");
        names.add("Дмитрий");
        names.add("Борис");
        names.add("Александр");
        names.add("Александр");
        names.add("Григорий");
        names.add("Александр");
        names.add("Василий");
        System.out.println(findMinSumPair(new ArrayList<>(Arrays.asList(45, 45, -24, -40, -2, -75, 3, 80))));
        System.out.println(findSimilar(names));
        System.out.println(findLocalMax(new ArrayList<Integer>(Arrays.asList(63, -30, -50, -50, 72, 68, 16, 9, 82, 10, -42, 51, 37, 0, -34, -92, 48, 59))));
        System.out.println(findSequence(new ArrayList<Integer>(Arrays.asList(4, 13, 7, 12, 15, 9, 8, 12, 6, 2, 8, 11, 1, 3, 14, 12))));

        //72,82,51.
    }
}