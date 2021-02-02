package ru.progwards.java1.lessons.interfaces;

public class Food  implements CompareWeight<Food>{

    private int weight;

    public Food(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public CompareResult compareWeight(Food food) {
        if (this.weight < food.getWeight()) return CompareResult.LESS;
        if (this.weight == food.getWeight()) return CompareResult.EQUAL;
        return CompareResult.GREATER;
    }

    @Override
    public void sort(Food[] a) {


    }


}
