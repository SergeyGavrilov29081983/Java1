package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {

    private int[] array;

    public DIntArray() {
        array = new int[0];

    }

    public void add(int num) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = num;
    }

    public void atInsert(int pos, int num) {
        array = Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, pos - 1, array, pos, array.length - pos);
        array[pos] = num;
    }

    public void atDelete(int pos) {
        System.arraycopy(array, pos + 1, array, pos, array.length - pos - 1);
        array = Arrays.copyOf(array, array.length - 1);
    }


    public int at(int pos) {
        return array[pos];
    }


}
