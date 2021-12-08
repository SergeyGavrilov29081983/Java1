package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class DynamicArray<T> {

    private int elementPosition;
    private T[] array;

    public DynamicArray() {
        elementPosition = 0;
        array = (T[]) new Object[10];
    }

    public void add(T t) {
        resize();
        array[elementPosition++] = t;
    }

    public void insert(int pos, T element) {
        try {
            if (array[pos] != null) {
                System.arraycopy(array, pos, array, pos + 1, getNotEmptyElementsCount() - pos);
                array[pos] = element;
            }
            array[pos] = element;
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException");
        }
    }

    public void remove(int pos) {
        try {
            System.arraycopy(array, pos + 1, array, pos, array.length - 1 - pos);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException");
        }
    }

    public T get(int pos) {
        return array[pos];
    }

    public int size() {
        return getNotEmptyElementsCount();
    }

    private int getNotEmptyElementsCount() {
        int counter = 0;
        for (T element : array) {
            if (element != null) {
                counter++;
            }
        }
        return counter;
    }

    private void resize() {
        boolean hasNull = false;
        for (T t : array) {
            if (t == null) {
                hasNull = true;
                break;
            }
        }
        if (!hasNull) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();

        dynamicArray.add(9);
        dynamicArray.add(9);
        dynamicArray.add(3);
        dynamicArray.add(3);
        dynamicArray.add(9);
        dynamicArray.add(9);
        dynamicArray.add(9);
        dynamicArray.add(9);
        dynamicArray.add(9);
        dynamicArray.add(9);
        dynamicArray.add(9);
        dynamicArray.add(9);
        dynamicArray.insert(8, 1);
        dynamicArray.remove(3);
        dynamicArray.insert(15, 1);
        dynamicArray.insert(30, 1);
        dynamicArray.remove(15);
        dynamicArray.remove(40);
        System.out.println(dynamicArray.get(8));
        System.out.println(dynamicArray.size());
    }
}
