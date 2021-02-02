package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;

public interface CompareWeight<T> {

    public CompareResult compareWeight(T obj);

    public static void sort(Animal[] a){
        Arrays.sort(a);
    }



    public enum CompareResult {
        LESS, EQUAL, GREATER;
    }

}

