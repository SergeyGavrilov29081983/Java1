package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {

    private T[][] array;
    private int pointerFirst;
    private int PointerSecond;

    MatrixIterator(T[][] array) {
        this.array = array;
        pointerFirst = 0;
        PointerSecond = 0;
    }

    @Override
    public boolean hasNext() {
        return PointerSecond < array[pointerFirst].length || pointerFirst < array.length - 1;
    }

    @Override
    public T next() {
        if (PointerSecond == array[pointerFirst].length) {
            pointerFirst++;
            PointerSecond = 0;
        }
        PointerSecond++;
        return array[pointerFirst][PointerSecond--];
    }
}
