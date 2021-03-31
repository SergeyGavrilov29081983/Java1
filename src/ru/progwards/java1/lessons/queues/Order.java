package ru.progwards.java1.lessons.queues;

import java.util.PriorityQueue;

public class Order  implements Comparable<Order>{

    private double sum;
    private int num;

    public Order(double sum) {
        if (sum >= 10000) {
            num = 3;
        } else if (sum > 10000 && sum <= 20000) {
            num = 2;
        } else {
            num = 1;
        }
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
