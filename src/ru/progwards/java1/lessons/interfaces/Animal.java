package ru.progwards.java1.lessons.interfaces;


import java.util.Objects;


import static ru.progwards.java1.lessons.interfaces.FoodKind.*;

public class Animal implements FoodCompare {

    private double weight;



    public Animal(double weight) {
        this.weight = weight;

    }

    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }

    public FoodKind getFoodKind() {
        return UNKNOWN;
    }

    public double getFood1kgPrice() {
        switch (FoodKind) {
            case HAY:
                return 20;
            case CORN:
                return 50;
            case UNKNOWN:
                return 0;
        }
        return 0;
    }

    public double getFoodPrice() {
        return  calculateFoodWeight() * getFood1kgPrice();

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

    public static void main(String[] args) {
        Animal animal = new Animal(20);
        System.out.println();
    }

}
