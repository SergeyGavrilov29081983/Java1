package ru.progwards.java1.lessons.interfaces;


public interface CompareWeight<T> {

    public CompareResult compareWeight(T obj);

    public void sort(Animal[] a);





    public enum CompareResult {
        LESS, EQUAL, GREATER;
    }

}

