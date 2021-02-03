package ru.progwards.java1.lessons.interfaces;

public class ArraySort {

    public static void sort(CompareWeight<Animal>[] a) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i].compareWeight((Animal) a[i + 1]) == CompareWeight.CompareResult.GREATER) {
                    isSorted = false;
                    Animal animal;
                    animal = (Animal) a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = animal;
                }
            }
        }

    }


    public static void main(String[] args) {

        Animal[] arr = {
                new Animal(59),
                new Animal(19),
                new Animal(60),
                new Animal(5),
                new Animal(8),
        };
        sort(arr);
        for (Animal animal : arr) {
            System.out.println(animal.getWeight());
        }
    }

}

