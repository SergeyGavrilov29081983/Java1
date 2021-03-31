package ru.progwards.java1.lessons.queues;


import java.util.PriorityQueue;

public class OrderQueue {
    private PriorityQueue<Order> queue = new PriorityQueue<>();

    public void add(Order order) {
        queue.offer(order);
    }

    public Order get() {
        return queue.poll();
    }
}
