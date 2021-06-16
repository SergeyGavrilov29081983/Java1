package ru.progwards.java1.lessons.datetime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Profiler {
    static Map<String, StatisticInfo> Sections = new HashMap<>();
    static Map<String, Long> startTime = new HashMap<>();
    static Map<String, Integer> counts = new HashMap<>();
    static Deque<String> qSections = new ArrayDeque<>();

    public static void enterSection(String name) {
        if (startTime.size() == 0 || !startTime.containsKey(name)) {
            counts.put(name, 1);
            qSections.addFirst(name);
        } else {
            counts.put(name, counts.get(name) + 1);
            if (!name.equals(qSections.peekFirst())) {
                qSections.addFirst(name);
            }
        }
        startTime.put(name, System.currentTimeMillis());
    }

    public static void exitSection(String name) {
        long fullTime = System.currentTimeMillis() - startTime.get(name);
        long selfTime = fullTime;
        long prevTime = 0;
        boolean isNested = false;
        while (!name.equals(qSections.peekFirst())) {
            isNested = true;
            String prevname = qSections.pollFirst();
            StatisticInfo prev = Sections.get(prevname);
            prevTime = prevTime + prev.fullTime;
        }
        if (isNested) {
            if (!Sections.isEmpty() && Sections.containsKey(name)) {
                StatisticInfo val = Sections.get(name);
                fullTime += val.fullTime;
                selfTime = fullTime - prevTime;
            } else {
                selfTime = selfTime - prevTime;
            }
        } else {
            if (!Sections.isEmpty() && Sections.containsKey(name)) {
                StatisticInfo val = Sections.get(name);
                fullTime += val.fullTime;
                selfTime += val.selfTime;
            }
        }
        StatisticInfo newSI = new StatisticInfo(name, (int) fullTime, (int) selfTime, counts.get(name));
        Sections.put(name, newSI);
    }

    public static List<StatisticInfo> getStatisticInfo() {
        Collection<StatisticInfo> temp = Sections.values();
        List<StatisticInfo> res = new ArrayList<>(temp);
        res.sort(StatisticInfo::compareTo);
        return res;
    }

    public static void main(String[] args) throws IOException {

        replaceF("123.txt");

    }

    static String createFolder(String name) {
        File file = new File(name);
        file.mkdir();
        Path p = Paths.get(name);
        return p.toAbsolutePath().getParent().getParent().toString();

    }

    static boolean replaceF(String name) {
        Path path = Paths.get(name);
        if (Files.exists(path)) {
            try {
                byte[] text = Files.readAllBytes(path);
                String result = new String(text, "UTF-8");
                String replace = result.replace('F', 'f');
                Files.write(path,replace.getBytes());
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
   /* который заменяет в файле все F на f, в случае ошибки вернуть false.
    Для реализации пользоваться методами java.nio.file.Files.*/
}

