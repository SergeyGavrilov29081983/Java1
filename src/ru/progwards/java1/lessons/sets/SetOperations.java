package ru.progwards.java1.lessons.sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetOperations {

    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        TreeSet<Integer> result = new TreeSet<>();
        for (Integer o : set1) {
            if (!set2.contains(o)) {
                result.add(o);
            }
        }
        return result;

    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        TreeSet<Integer> result = new TreeSet<>();
        for (Integer o : set1) {
            if (!set2.contains(o)) {
                result.add(o);
            }
        }
        for (Integer o : set2) {
            if (!set1.contains(o)) {
                result.add(o);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(difference(new HashSet<>(Arrays.asList(1, 4, 5, 6, 7, 8, 10)), new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 7, 8, 9))));  //6,10
        System.out.println(symDifference(new HashSet<>(Arrays.asList(0, 2, 4, 5, 6, 7, 8, 10)), new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 7))));  //1,3,6,8,10.
    }

}


