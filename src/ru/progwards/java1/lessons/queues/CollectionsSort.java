package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {

    public static void mySort(Collection<Integer> data) {

        List<Integer> list = new ArrayList<>(data);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) > list.get(j)) {
                    int a1 = list.get(i);
                    int a2 = list.get(j);
                    list.add(i, a1);
                    list.add(j, a2);
                }
            }
        }
        data.clear();
        data.addAll(list);
    }

    public static void minSort(Collection<Integer> data) {
        List<Integer> result = new ArrayList<>();
        while (!data.isEmpty()) {
            int min = Collections.min(data);
            result.add(min);
            data.remove(min);
        }
        data.addAll(result);
    }

    static void collSort(Collection<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        Collections.sort(result);
        data.clear();
        data.addAll(result);
    }

    static Collection<String> compareSort() {

        long start = System.currentTimeMillis();
        mySort(randomCollection());
        long time = System.currentTimeMillis() - start;
        Sorter mySort = new Sorter("mySort", time);

        start = System.currentTimeMillis();
        minSort(randomCollection());
        time = System.currentTimeMillis() - start;
        Sorter minSort = new Sorter("minSort", time);

        start = System.currentTimeMillis();
        collSort(randomCollection());
        time = System.currentTimeMillis() - start;
        Sorter collSort = new Sorter("collSort", time);

        TreeSet<Sorter> sorterTreeSet = new TreeSet<>();
        sorterTreeSet.add(minSort);
        sorterTreeSet.add(mySort);
        sorterTreeSet.add(collSort);

        List<String> result = new ArrayList<>();
        for (Sorter sorter : sorterTreeSet) {
            result.add(sorter.name);
        }
        return result;
    }

    private static Collection<Integer> randomCollection() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        int number = 0;
        int ITERATIONS = 10000;

        for (int i = 0; i < ITERATIONS; i++) {
            number = random.nextInt();
            list.add(number);
        }
        return list;
    }

    static class Sorter implements Comparable<Sorter> {

        String name;
        long time;

        public Sorter(String name, long time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public int compareTo(Sorter sorter) {

            if (this.time > sorter.time) {
                return 1;
            }
            if (this.time < sorter.time) {
                return -1;
            }

            if (this.name.charAt(0) > sorter.name.charAt(0)) {
                return 1;
            }
            if (this.name.charAt(0) < sorter.name.charAt(0)) {
                return -1;
            }
            return Character.compare(this.name.charAt(1), sorter.name.charAt(1));
        }
    }
}

