package ru.progwards.java1.lessons.classes;

public class Animal {

    private double weight;

    public Animal(double weight) {
        this.weight = weight;
    }

    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }

    public FoodKind getFoodKind() {
        return FoodKind.UNKNOWN;
    }

    public double getWeight() {
        return weight;
    }

    public double getFoodCoeff() {
        return 0.02;
    }

    public double calculateFoodWeight(){
        return getWeight() * getFoodCoeff();
    }

    @Override
    public String toString() {
        return "I am " + getKind() + ", " + "eat " + getFoodKind();
    }

    public String toStringFull() {
    return toString() + " " + calculateFoodWeight();
    }
}
