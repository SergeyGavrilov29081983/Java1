package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FruitBox<T> {

    private final ArrayList<T> box = new ArrayList<>();

    public static void main(String[] args) {
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Orange o1 = new Orange();
        Orange o2 = new Orange();
        FruitBox box1 = new FruitBox();
        box1.add(a2, a1, a1, o2, a2, o1, o2);
        FruitBox box2 = new FruitBox();
        box2.add(a2);
        FruitBox box3 = new FruitBox();
        box3.add(o1);
        System.out.println(box1.compareTo(box2));
        System.out.println("box1=" + box1.getWeight());
        System.out.println("box2=" + box2.getWeight());
        System.out.println("box3=" + box3.getWeight());

        box1.moveTo(box2);
        System.out.println("box1.moveTo(box2)");

        System.out.println("box1=" + box1.getWeight());
        System.out.println("box2=" + box2.getWeight());

        box3.moveTo(box1);
        System.out.println("box3.moveTo(box1)");

        System.out.println("box1=" + box1.getWeight());
        System.out.println("box3=" + box3.getWeight());

        try {
            System.out.print("box1.moveTo(box2) - ");
            box1.moveTo(box2);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("box1=" + box1.getWeight());
        System.out.println("box2=" + box2.getWeight());
    }

    @SafeVarargs
    public final void add(T... items) {
        List<T> list = new ArrayList<T>(Arrays.asList(items));
        if (!list.isEmpty()) {
            if (list.get(0) instanceof Apple) {
                for (T item : items) {
                    if (item instanceof Apple) {
                        box.add(item);
                    }
                }
            } else {
                for (T item : items) {
                    if (item instanceof Orange) {
                        box.add(item);
                    }
                }
            }
        }
    }

    public float getWeight() {
        float boxWeight = 0f;
        for (T item : box) {
            if (item instanceof Apple) {
                boxWeight = (float) box.size() * ((Apple) item).weight;
                break;
            } else {
                boxWeight = (float) box.size() * ((Orange) item).weight;
                break;
            }
        }
        return boxWeight;
    }

    public void moveTo(FruitBox<T> item) {
        List<T> newBox = new ArrayList<>(box);
        box.clear();
        box.addAll(item.box);
        item.box.clear();
        item.box.addAll(newBox);
    }

    public int compareTo(FruitBox<T> box) {
        return Float.compare(box.getWeight(), this.getWeight());
    }
}

class Fruit {
}

class Apple extends Fruit {
    float weight = 1.0f;

}

class Orange extends Fruit {
    float weight = 1.5f;

}
