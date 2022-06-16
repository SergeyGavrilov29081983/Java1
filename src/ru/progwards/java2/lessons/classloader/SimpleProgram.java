package ru.progwards.java2.lessons.classloader;

import javassist.CannotCompileException;
import javassist.NotFoundException;

public class SimpleProgram {

    private void printMessage() {
        System.out.println("hello java!!!");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        SimpleProgram simpleProgram = new SimpleProgram();
        simpleProgram.printMessage();
        Class.forName("ru.progwards.java2.lessons.classloader.SimpleProgram", true, ClassLoader.getSystemClassLoader());
        try {
            SystemProfiler.hackProgram("ru.progwards.java2.lessons.classloader.SimpleProgram.class");
        } catch (NotFoundException | CannotCompileException e) {
            System.out.println("cant find class");
        }
    }
}
