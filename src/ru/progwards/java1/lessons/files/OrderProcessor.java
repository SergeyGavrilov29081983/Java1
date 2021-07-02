package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class OrderProcessor {

    public static final String PATTERN = "***-******-****.csv";
    private final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(PATTERN);
    private final Path path;
    private final  Map<String, List<String>> result = new HashMap<>();

    public OrderProcessor(String startPath) {
        path = Paths.get(startPath);

    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        AtomicInteger counter = new AtomicInteger(0);
        try (Stream<Path> paths = Files.walk(path, 999)) {
            paths.filter(Files::isRegularFile).forEach(f -> {
                f.getFileName();
                if (pathMatcher.matches(f)) {
                    Path path = Paths.get(String.valueOf(f.toAbsolutePath()));
                    try {
                        List<String> list = Files.readAllLines(path);
                        result.put(f.getFileName().toString(), list);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    counter.getAndIncrement();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(counter.intValue() > 0) {
            result.clear();
        }
        return counter.intValue();
    }

    void someMethod (LocalDate start, LocalDate finish, String shopId) {

        try {
            Files.walkFileTree(Paths.get(String.valueOf(path)).normalize(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {

                    }
                    return FileVisitResult.CONTINUE;
                }
                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException e) {
                        return FileVisitResult.CONTINUE;
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*загружает заказы
    за указанный диапазон дат, с start до finish, обе даты включительно. Если start == null, значит нет ограничения
    по дате слева, если finish == null, значит нет ограничения по дате справа, если shopId == null - грузим для всех магазинов
    При наличии хотя бы одной ошибке в формате файла, файл полностью игнорируется, т.е. Не поступает в обработку.
    Метод возвращает количество файлов с ошибками. При этом, если в классе содержалась информация, ее надо удалить*/

}
