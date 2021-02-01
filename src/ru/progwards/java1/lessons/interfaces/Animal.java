package ru.progwards.java1.lessons.interfaces;


import ru.progwards.java1.lessons.arrays.ArraySort;

import java.util.Objects;

import static ru.progwards.java1.lessons.interfaces.FoodKind.UNKNOWN;

public class Animal implements FoodCompare, CompareWeight<Animal> {

    FoodKind foodKind = getFoodKind();
    private double weight;
    ArraySort sort;

    public Animal(double weight) {
        this.weight = weight;
    }

    public static void main(String[] args) {
        Animal animal = new Cow(1D);
        System.out.println(animal.getFood1kgPrice());
    }

    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }

    public FoodKind getFoodKind() {
        return UNKNOWN;
    }

    public double getFood1kgPrice() {
        switch (foodKind) {
            case HAY:
                return 20;
            case CORN:
                return 50;
            case UNKNOWN:
                return 0;
            default:
                return 0;
        }
    }

    public double getFoodPrice() {
        return calculateFoodWeight() * getFood1kgPrice();

    }

    public double getWeight() {
        return weight;
    }

    public double getFoodCoeff() {
        return 0.02;
    }

    public double calculateFoodWeight() {
        return getWeight() * getFoodCoeff();
    }

    @Override
    public String toString() {
        return "I am " + getKind() + ", " + "eat " + getFoodKind();
    }

    public String toStringFull() {
        return toString() + " " + calculateFoodWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    @Override
    public int compareFoodPrice(Animal animal) {
        return Double.compare(this.getFoodPrice(), animal.getFoodPrice());
    }

    @Override
    public CompareResult compareWeight(Animal animal) {
        if (this.weight < animal.weight) return CompareResult.LESS;
        if (this.weight == animal.weight) return CompareResult.EQUAL;
        return CompareResult.GREATER;
    }




}
