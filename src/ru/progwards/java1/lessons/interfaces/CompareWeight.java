package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {

    public CompareResult compareWeight(Animal animal);

    public enum CompareResult {
        LESS, EQUAL, GREATER;
    }

}

