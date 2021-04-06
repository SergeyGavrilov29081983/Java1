package ru.progwards.java1.lessons.queues;

import java.util.*;


public class CollectionsSort {

    public static void mySort(Collection<Integer> data) {
        Integer[] result = new Integer[data.size()];
        data.toArray(result);
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if (result[i] > result[j]) {
                    int sortedDigit = result[i];
                    result[i] = result[j];
                    result[j] = sortedDigit;
                }
            }
        }
        data.clear();
        data.addAll(Arrays.asList(result));
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

