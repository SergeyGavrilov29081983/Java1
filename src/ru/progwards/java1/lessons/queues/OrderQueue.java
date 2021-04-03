package ru.progwards.java1.lessons.queues;


import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderQueue {
    Comparator<Order> comparator = Comparator.comparingDouble(Order::getSum);

    private PriorityQueue<Order> queue = new PriorityQueue<>(comparator);

    public void add(Order order) {
        queue.offer(order);
    }

    public Order get() {
        return queue.poll();
    }
}
