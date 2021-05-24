package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.*;

public class Profiler {

    static class StackItem {
        public int fullTime;// полное время выполнения секции в миллисекундах.
        public int selfTime;// чистое время выполнения секции в миллисекундах.
        public LocalDateTime startTime;

        public StackItem(int i, int i1, LocalDateTime now) {
            this.fullTime = i;
            this.selfTime = i1;
            this.startTime = now;
        }
    }

    static List<StackItem> stack = new ArrayList<>();
    static TreeMap<String, StatisticInfo> tree = new TreeMap<>();


    public static void enterSection(String name) {
        {
            stack.add(new StackItem(1,1,LocalDateTime.now()));
        }
        tree.put(name, new StatisticInfo(name, 1, 1, 1));
    }

    public static void exitSection(String name) {
        {
            stack.add(new StackItem(1,1,LocalDateTime.now()));
        }
        tree.put(name, new StatisticInfo(name, 1, 1, 1));
    }

    public static List<StatisticInfo> getStatisticInfo() {
        Collection<StatisticInfo> temp = tree.values();
        List<StatisticInfo> res = new ArrayList<>(temp);
        res.sort(StatisticInfo::compareTo);
        return res;
    }




    public static void main(String[] args) {
        Profiler t = new Profiler();

        enterSection("Process1");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        exitSection("Process1");
        enterSection("Process1");
       /* try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        exitSection("Process1");
        enterSection("Process1");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        exitSection("Process1");
        enterSection("Process1");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        enterSection("Process2");
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        enterSection("Process3");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        exitSection("Process3");
        exitSection("Process2");
        enterSection("Process2");
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        enterSection("Process3");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        exitSection("Process3");
        exitSection("Process2");
        exitSection("Process1");*/

        List<StatisticInfo> res = getStatisticInfo();
        for (StatisticInfo tt : res) {
            System.out.println(tt.sectionName);
            System.out.println(tt.fullTime);
            System.out.println(tt.selfTime);
            System.out.println(tt.count);
        }
    }
}
