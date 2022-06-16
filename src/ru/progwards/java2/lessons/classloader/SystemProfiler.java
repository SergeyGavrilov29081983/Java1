package ru.progwards.java2.lessons.classloader;

import javassist.*;

public class SystemProfiler {

    public static void hackProgram(String className) throws NotFoundException, CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get(className.replace('/', '.'));
        CtMethod ctMethod = ctClass.getDeclaredMethod("main"); // имя метода
        ctMethod.addLocalVariable("start", CtClass.longType);
        ctMethod.insertBefore("start = System.currentTimeMillis();");
        ctMethod.insertAfter("System.out.println(\"время выполнения\" + (System.currentTimeMillis() - start));");
    }


}
