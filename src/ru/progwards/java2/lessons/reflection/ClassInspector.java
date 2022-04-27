package ru.progwards.java2.lessons.reflection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;

public class ClassInspector {

    /*  Реализовать метод public static void inspect(String clazz),
      который выводит в файл с именем равным имени класса и расширением
      java содержимое класса в формате:
      class <имя> {
    <свойство 1>
    <свойство 2>
    ...
    <конструктор 1>
    <конструктор 2>
    ...
    <метод 1>
    <метод 2>
    ...
      }
      каждый конструктор имеет формат:
  <модификаторы> <имя>(<параметр 1>, <параметр 2>, ...) {}
      где каждое свойство имеет формат:
  <модификаторы> <тип> <имя>;
      каждый метод имеет формат:
  <модификаторы> <тип> <имя>(<параметр 1>, <параметр 2>, ...) {}
      и каждый параметр имеет формат:
  <тип> <имя>
  */
    public static void inspect(String clazz) {

        try {
            Class thisClass = Class.forName(clazz);
            String className = thisClass.getSimpleName();
            Field[] fields = thisClass.getDeclaredFields();
            Constructor[] constructors = thisClass.getDeclaredConstructors();
            Method[] methods = thisClass.getDeclaredMethods();

            String relativePath = "/home/sergey/examples/ " + className + ".java";
            File file = new File(relativePath);
            try {
                if (file.createNewFile()) {
                    try (FileWriter writer = new FileWriter(file, false)) {
                        writer.write(thisClass.toGenericString() + " {" + System.lineSeparator());

                        for (Field f : fields) {
                            String type = f.getGenericType().toString();
                            int index = type.indexOf(" ");
                            if (index != -1) {
                                type = type.substring(index + 1).trim();
                            }
                            writer.write("\t" + Modifier.toString(f.getModifiers()) + " " +
                                    stringSplitter(type) + " " + f.getName() + ";" + System.lineSeparator());
                        }
                        writer.write(System.lineSeparator());

                        for (Constructor c : constructors) {
                            Parameter[] parameters = c.getParameters();
                            writer.write("\t" + Modifier.toString(c.getModifiers()) + " " +
                                    className + getParams(parameters) + " {}" + System.lineSeparator());
                        }
                        writer.write(System.lineSeparator());

                        for (Method m : methods) {
                            Parameter[] parameters = m.getParameters();
                            writer.write("\t" + Modifier.toString(m.getModifiers()) + " " +
                                    stringSplitter(m.getGenericReturnType().getTypeName()) + " " +
                                    m.getName() + getParams(parameters) + " {}" + System.lineSeparator());
                        }
                        writer.write("}");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getParams(Parameter[] parameters) {
        String params = "()";
        if (parameters.length != 0) {
            params = "(";
            for (Parameter p : parameters) {
                params += stringSplitter(p.getParameterizedType().getTypeName()) + " " + p.getName() + ", ";
            }
            params = params.substring(0, params.length() - 2);
            params += ")";
        }
        return params;
    }

    public static String stringSplitter(String toSplit) {
        String[] arr = toSplit.split("\\.");
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {
        inspect("ru.progwards.java2.lessons.reflection.Person");
        /*Person myClass = new Person();
        String number = myClass.getName();
        String name = null; //no getter =(
        System.out.println(number + name);//output 0null
        try {
            Field field = myClass.getClass().getDeclaredField("name");
            field.setAccessible(true);
            name = (String) field.get(myClass);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(number + name);//output 0default
    }*/
    }

}
