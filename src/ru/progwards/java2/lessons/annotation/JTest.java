package ru.progwards.java2.lessons.annotation;

import org.junit.Before;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JTest {
    @Before
    void run(String name) throws RuntimeException {
        Class clazz = null;
        int counterBefore = 0;
        int counterAfter = 0;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        sort(declaredMethods);
        for (Method method : declaredMethods) {
            Annotation before = method.getAnnotation(Before.class);
            Annotation after = method.getAnnotation(After.class);
            if (before != null) {
                counterBefore++;
            }
            if (after != null) {
                counterAfter++;
            }
        }
        if (counterBefore > 1 || counterAfter > 1) {
            throw new RuntimeException("to many before or after");
        }
        for (Method method : declaredMethods) {
            Annotation before = method.getAnnotation(Before.class);
            if (before != null) {
                try {
                    method.invoke(clazz);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                methodsInvoker(declaredMethods, clazz);
            } else {
                methodsInvoker(declaredMethods, clazz);
            }
        }

        for (Method method : declaredMethods) {
            Annotation after = method.getAnnotation(After.class);
            if (after != null) {
                try {
                    method.invoke(clazz);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void methodsInvoker(Method[] methods, Object clazz) {
        Annotation test;
        for (Method method : methods) {
            test = method.getAnnotation(Test.class);
            if (test != null) {
                try {
                    method.setAccessible(true);
                    method.invoke(clazz, 1, 2);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void sort(Method[] methods) {
        int min = 0;
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getAnnotation(Test.class) != null) {
                min = methods[i].getAnnotation(Test.class).priority();
            }
            int min_i = i;
            for (int j = i + 1; j < methods.length; j++) {
                if (methods[j].getAnnotation(Test.class) != null && methods[j].getAnnotation(Test.class).priority() < min) {
                    min = methods[j].getAnnotation(Test.class).priority();
                    min_i = j;
                }
            }
            if (i != min_i) {
                Method tmp = methods[i];
                methods[i] = methods[min_i];
                methods[min_i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        JTest jTest = new JTest();
        jTest.run("ru.progwards.java2.lessons.tests.calc.SimpleCalculator");
    }
}

