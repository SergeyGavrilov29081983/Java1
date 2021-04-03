package ru.progwards.java1.lessons.queues;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Order  implements Comparable<Order>{

    private double sum;
    private int num;
    private AtomicInteger id = new AtomicInteger(1);

    public Order(double sum) {
        this.num = id.incrementAndGet();
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Order order) {
        return Integer.compare(this.num, order.num);
    }
}
