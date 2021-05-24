package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Profiler {


    static List<StackItem> stack = new ArrayList<>();
    static TreeMap<String, StatisticInfo> tree = new TreeMap<>();


    public static void enterSection(String name) {
        stack.add(new StackItem(1,2, LocalDateTime.now()));
    }

    public static void exitSection(String name) {
    }

    public static List<StatisticInfo> getStatisticInfo() {
        return null;
    }

    static class StackItem {

        public int fullTime;// полное время выполнения секции в миллисекундах.
        public int selfTime;// чистое время выполнения секции в миллисекундах.
        public LocalDateTime startTime;

        public StackItem(int fullTime, int selfTime, LocalDateTime startTime){
            this.fullTime = fullTime;
            this.selfTime = selfTime;
            this.startTime = startTime;
        }
    }
}
