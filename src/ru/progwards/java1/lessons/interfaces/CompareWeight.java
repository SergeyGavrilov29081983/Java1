package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight<T> {

    CompareResult compareWeight(T obj);

    public enum CompareResult {
        LESS, EQUAL, GREATER;
    }
}

