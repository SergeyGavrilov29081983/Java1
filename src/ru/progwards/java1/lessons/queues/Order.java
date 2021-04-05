package ru.progwards.java1.lessons.queues;

import java.util.concurrent.atomic.AtomicInteger;

public class Order implements Comparable<Order> {

    private static final AtomicInteger id = new AtomicInteger(0);
    private double sum;
    private int num;
    private int category;

    public Order(double sum) {
        this.num = id.incrementAndGet();
        this.sum = sum;
        if (sum >= 20000.0) {
            this.category = 3;
        } else if (sum >= 10000.0) {
            this.category = 2;
        } else {
            this.category = 1;
        }
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Order o) {
        if (this.category > o.category) {
            return -1;
        }
        if (this.category < o.category) {
            return 1;
        }
        return Integer.compare(this.num, o.num);
    }
}



