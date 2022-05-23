package ru.progwards.java2.lessons.classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PathLoader extends ClassLoader {
    Logger logger = Logger.getLogger(PathLoader.class.getName());
    final static String PATH_OF_TASKS = "";
    final static String DOT_CLASS = ".class";
    private static final PathLoader pathLoader = new PathLoader(PATH_OF_TASKS);
    private final String basePath;

    public PathLoader(String basePath) {
        this(basePath, ClassLoader.getSystemClassLoader());
    }

    public PathLoader(String basePath, ClassLoader classLoader) {
        super(classLoader);
        this.basePath = basePath;
        try {
            LogManager.getLogManager().readConfiguration(PathLoader.class.getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        Path classPathName = null;
        try {
            String classPath = className.replace(".", "/");
            classPathName = Paths.get(basePath + classPath + DOT_CLASS);
            if (Files.exists(classPathName)) {
                Date date = new Date();
                byte[] b = Files.readAllBytes(classPathName);
                Class<?> aClass = defineClass(className, b, 0, b.length);
                logger.log(Level.INFO, date.toString() + classPathName +  " загружен из " + basePath + "успешно");
                return aClass;
            } else {
                return findSystemClass(className);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Date date = new Date();
            logger.log(Level.INFO, date.toString() + classPathName + " ошибка загрузки " + Arrays.deepToString(ex.getStackTrace()));
            throw new ClassNotFoundException(className);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        PathLoader loader = new PathLoader("root/data/package/");
        loader.findClass("test");
    }
}


    /*Требуется реализовать систему, которая позволяет подгружать
    исправленный программный код (патчи) работающих модулей "на лету".
    Для этого мы размещаем систему папок <root>/<date>/<package>/<class>

        root - некая корневая папка
        date - дата выпуска патча в формате ггггммдд - 20200425
        package - структура папок для пакета, возможно с подпакетами
class - скомпилированный class-файл. Если в пакете более одного class-файла, то надо использовать их все.

        Каждая папка date содержит разное количество классов. Один и тот же класс может присутствовать в нескольких папках (был исправлен несколько раз), некоторые не разу. В одной папке date может быть несколько исправленных файлов, в другой только один, и такой, каких нет в предыдущей.

        Необходимо реализовать свой ClassLoader, который просканирует каталог root, найдет там самую свежую реализацию  нужного класса, и загрузит ее.

        Вести лог загрузки - файл patchloader.log, куда писать информацию в формате

        дд.мм.гггг чч.мм.сс <полное имя класса с пакетом> загружен из <полный путь до папки с пакетом> успешно

        или

        дд.мм.гггг чч.мм.сс <полное имя класса с пакетом> ошибка загрузки <описание ошибки>
*/