package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Creator {

    public static Collection<Integer> fillEven(int n) {
        Collection<Integer> collection = new ArrayList<>(n);
        for (int i = 2; i <= n * 2; i++) {
            if (i % 2 == 0) {
                collection.add(i);
            }
        }
        collection.forEach(System.out::println);
        return collection;
    }

    public static Collection<Integer> fillOdd(int n) {
        Collection<Integer> collection = new ArrayList<>(n);
        for (int i = 1; i <= n * 2; i++) {
            if (i % 2 != 0) {
                collection.add(i);
            }
        }
        Collections.reverse((List<?>) collection);
        collection.forEach(System.out::println);
        return collection;

    }


    public static Collection<Integer> fill3(int n) {
        Collection<Integer> collection = new ArrayList<>();
        int j;
        for (int i = 0; i < n; i++) {
            j = i * 3;
            collection.add(j);
            collection.add((int)Math.pow(j, 2));
            collection.add((int)Math.pow(j, 3));
        }
        return collection;
    }



    /*    }-создать коллекцию и заполнить ее тройками чисел.
        Каждая тройка создается по алгоритму:первое число тройки-индекс числа в коллекции,второе-тот же индекс
        в квадрате,третье-тот же индекс в кубе,количество элементов в коллекции n*3


        }*/


    public static void main(String[] args) {
        fillEven(7);
        fillOdd(15);
    }
}

