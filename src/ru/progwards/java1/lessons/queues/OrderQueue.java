package ru.progwards.java1.lessons.queues;


import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderQueue {
    Comparator<Order> comparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return Integer.compare(o1.getNum(), o2.getNum());
        }
    };
    private PriorityQueue<Order> queue = new PriorityQueue<>(comparator);

    public void add(Order order) {
        queue.offer(order);
    }

    public Order get() {
        return queue.poll();
    }
}
