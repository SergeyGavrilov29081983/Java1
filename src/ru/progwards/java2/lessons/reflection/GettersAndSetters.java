package ru.progwards.java2.lessons.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;

public class GettersAndSetters {
    public static boolean HasGetter(String type, String name, Method[] methods) {
        String nameUpperCase = name.substring(0, 1).toUpperCase() + name.substring(1);
        for (Method m : methods) {
            Parameter[] parameters = m.getParameters();
            if (m.getName().equals("get" + nameUpperCase)) {
                if (!Modifier.toString(m.getModifiers()).contains("static")) {
                    if (parameters.length == 0) {
                        if (m.getGenericReturnType().getTypeName().equals(type)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean HasSetter(String type, String name, Method[] methods) {
        String nameUpperCase = name.substring(0, 1).toUpperCase() + name.substring(1);
        for (Method m : methods) {
            Parameter[] parameters = m.getParameters();
            if (m.getName().equals("set" + nameUpperCase)) {
                if (!Modifier.toString(m.getModifiers()).contains("static")) {
                    if (parameters.length == 1) {
                        if (parameters[0].getParameterizedType().getTypeName().equals(type)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void check(String clazz) {
        try {
            Class thisClass = Class.forName(clazz);
            Field[] fields = thisClass.getDeclaredFields();
            Method[] methods = thisClass.getMethods();
            int count = 0;
            String[] types = new String[fields.length];
            String[] names = new String[fields.length];
            for (Field f : fields) {
                if (Modifier.toString(f.getModifiers()).contains("private")) {
                    types[count] = f.getGenericType().toString();
                    int index = types[count].indexOf(" ");
                    if (index != -1) {
                        types[count] = types[count].substring(index + 1).trim();
                    }
                    names[count] = f.getName();
                    count++;
                }
            }
            String out = "";
            for (int i = 0; i < count; i++) {
                String nameUpperCase = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
                if (!HasGetter(types[i], names[i], methods)) {
                    out += "public " + stringSplitter(types[i]) + " get" + nameUpperCase + "() \n";
                }
                if (!HasSetter(types[i], names[i], methods)) {
                    out += "public void set" + nameUpperCase + "(" + stringSplitter(types[i]) + " " + names[i] + ") \n";
                }
            }
            System.out.println(out);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String stringSplitter(String toSplit) {
        String[] arr = toSplit.split("\\.");
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {

        printAnnotation();

    }

   static void setName(Person person, String name) {
        Class clazz = person.getClass();
        try {
            Field field = clazz.getDeclaredField("name");
            field.setAccessible(true);
            field.set(person, name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static void callSetName(Person person, String name) {
        try {
           Method method =  person.getClass().getDeclaredMethod("setName", String.class);
           method.setAccessible(true);
            try {
                method.invoke(person, name);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    static void printAnnotation() {
        Class clazz = Greetings.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m: methods) {
            AnnotationTest test = m.getAnnotation(AnnotationTest.class);
            if(test != null) {
                System.out.println(m.getName() + " " + test.text());
            }
        }
    }

    static Person callConstructor(String name) {
        Class clazz = Person.class;

        Constructor constructor;
        Object obj;
        try {
           constructor = clazz.getDeclaredConstructor(String.class);
           constructor.setAccessible(true);
            try {
               obj =  constructor.newInstance(name);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return (Person) obj;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AnnotationTest {
    String text() default "Всегда говори привет";
}




class Greetings {
    void hello() {}
    @AnnotationTest
    void goodday() {}
    void goodnight() {}
    void hi() {};
}
