package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Field;

public class ClassInspector {

    public static void main(String[] args) {
        Person myClass = new Person();
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
    }
}
