package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderProcessor {

    public static final String PATTERN = "***-******-****.csv";
    private final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(PATTERN);
    private final Path path;
    List<Order> infoList = new ArrayList<>();


    public OrderProcessor(String startPath) {
        this.path = Paths.get(startPath);
    }

    public static boolean isPeriod(LocalDate start, LocalDate finish, LocalDate period) {
        if (start == null && finish == null) {
            return true;
        }
        if (start == null && (period.isEqual(finish) || period.isBefore(finish))) {
            return true;
        }
        if (finish == null && (period.isEqual(start) || period.isAfter(start))) {
            return true;
        }
        return (period.isEqual(start) || period.isAfter(start)) && (period.isEqual(finish) || period.isBefore(finish));
    }

    public Order loadOrder(Path path) throws IOException {
        Order res = new Order();
        String fileName = path.getFileName().toString();
        res.shopId = fileName.substring(0, 3);
        res.orderId = fileName.substring(4, 10);
        res.customerId = fileName.substring(11, 15);

        FileTime lmt = Files.getLastModifiedTime(path);
        Instant i = lmt.toInstant();
        ZoneId defaultZone = ZoneId.of("Europe/Moscow");
        res.datetime = LocalDateTime.ofInstant(i, defaultZone);

        res.items = null;
        res.sum = 0;
        try {
            List<String> fileLines = Files.readAllLines(path);
            res.items = new ArrayList<>();
            Iterator<String> it = fileLines.iterator();
            while (it.hasNext()) {
                String[] item = it.next().split(",");
                try {
                    OrderItem one = new OrderItem();
                    one.googsName = item[0];
                    one.count = Integer.parseInt(item[1].trim());
                    one.price = Double.parseDouble(item[2].trim());
                    res.items.add(one);
                    res.sum += one.price * one.count;
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " ошибка в структуре файла");
                    return null;
                }
            }

            res.items.sort(new Comparator<OrderItem>() {
                @Override
                public int compare(OrderItem o1, OrderItem o2) {
                    return o1.googsName.compareTo(o2.googsName);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage() + " ошибка при чтении файла");
            return null;
        }
        return res;
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        AtomicInteger counter = new AtomicInteger(0);
        String pattern = shopId;
        if (shopId == null) {
            pattern = "???";
        }

        PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:**/" + pattern + "-??????-????.csv");

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) {
                    if (pm.matches(p)) {
                        try {
                            FileTime lmt = Files.getLastModifiedTime(p);
                            Instant i = lmt.toInstant();
                            ZoneId defaultZone = ZoneId.of("Europe/Moscow");
                            LocalDateTime ldt = LocalDateTime.ofInstant(i, defaultZone);
                            if (isPeriod(start, finish, ldt.toLocalDate())) {
                                Order tmpOrder = loadOrder(p);
                                if (tmpOrder != null) {
                                    infoList.add(tmpOrder);
                                } else {
                                    counter.incrementAndGet();
                                }
                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path p, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return counter.intValue();
    }


    public List<Order> process(String shopId) {
        List<Order> result = new ArrayList<>();
        if (shopId == null) {
            result.addAll(infoList);
        } else {
            for (Order one : infoList) {
                if (one.shopId.equals(shopId)) {
                    result.add(one);
                }
            }
        }
        result.sort(Comparator.comparing(o -> o.datetime));
        return result;
    }

    public Map<String, Double> statisticsByShop() {
        Map<String, Double> result = new TreeMap<>();
        for (Order one : infoList) {
            if (!result.isEmpty() && result.containsKey(one.shopId)) {
                result.put(one.shopId, result.get(one.shopId) + one.sum);
            } else {
                result.put(one.shopId, one.sum);
            }
        }
        return result;
    }

    public Map<String, Double> statisticsByGoods() {
        Map<String, Double> result = new TreeMap<>();
        for (Order one : infoList) {
            for (OrderItem one2 : one.items) {
                if (!result.isEmpty() && result.containsKey(one2.googsName)) {
                    result.put(one2.googsName, result.get(one2.googsName) + one2.price * one2.count);
                } else {
                    result.put(one2.googsName, one2.price * one2.count);
                }
            }
        }
        return result;
    }

    public Map<LocalDate, Double> statisticsByDay() {
        Map<LocalDate, Double> result = new TreeMap<>();
        for (Order one : infoList) {
            LocalDate oneDate = one.datetime.toLocalDate();
            if (!result.isEmpty() && result.containsKey(oneDate)) {
                result.put(oneDate, result.get(oneDate) + one.sum);
            } else {
                result.put(oneDate, one.sum);
            }
        }
        return result;
    }
}
