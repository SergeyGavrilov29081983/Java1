package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class ArraySort {

    @SafeVarargs
    public static <T extends Comparable<T>> T[] sort(T...array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1].compareTo(array[j]) > 0) {
                    T tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        Integer[] array = {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(sort(array)));
    }
}
