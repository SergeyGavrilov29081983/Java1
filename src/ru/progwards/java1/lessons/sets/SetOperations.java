package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Iterator;
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
            if (!set1.contains(set2)) {
                result.add(o);
            }
        }
        return result;

    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        TreeSet<Integer> result = new TreeSet<>();
        for (Integer o : set1) {
            if (!set2.contains(set1)) {
                result.add(o);
            }
        }
        return result;
    }

}


