package ru.progwards.java1.lessons.interfaces;


import java.util.Arrays;

public final class ArraySort implements CompareWeight<Animal> {

    @Override
    public CompareResult compareWeight(Animal obj) {
        return null;
    }



    public static void sort(Animal[] animals) {


        /*for (int i = 0; i < animals.length; i++) {
            int min = (int) animals[i].getWeight();
            int min_i = i;
            for (int j = i + 1; j < animals.length; j++) {
                if (animals[j].getWeight() < min) {
                    min = (int)animals[j].getWeight();
                    min_i = j;
                }
            }
            if (i != min_i) {
                int tmp = (int)animals[i].getWeight();
                animals[i].setWeight(animals[min_i].getWeight());
                animals[min_i].setWeight(tmp);
            }
        }*/
    }

    public static void main(String[] args) {



    }
}

