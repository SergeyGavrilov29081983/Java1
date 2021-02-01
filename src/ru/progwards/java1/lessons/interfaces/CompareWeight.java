package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight<T> {

    public CompareResult compareWeight(T obj);

    //public void sort(int[] a);




    public enum CompareResult {
        LESS, EQUAL, GREATER;
    }

}

