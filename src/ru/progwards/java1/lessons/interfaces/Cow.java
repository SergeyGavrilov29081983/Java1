package ru.progwards.java1.lessons.interfaces;

public class Cow extends Animal {



    public Cow(double weight) {
        super(weight);
        getFoodKind();
    }



    @Override
    public AnimalKind getKind() {
        return AnimalKind.COW;
    }

    @Override
    public FoodKind getFoodKind() {
        return FoodKind.HAY;
    }

public void setFoodKind(FoodKind foodKind) {
        this.foodKind = getFoodKind();
}

    @Override
    public double getFoodCoeff() {
        return 0.05;
    }
}
