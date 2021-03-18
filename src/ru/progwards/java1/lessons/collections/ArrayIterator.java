package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int pointer;

    ArrayIterator(T[] array) {
        this.array = array;
        this.pointer = 0;
    }

    @Override
    public boolean hasNext() {
        return pointer < array.length;
    }

    @Override
    public T next() {
        pointer += 1;
        return array[pointer - 1];
    }
}

