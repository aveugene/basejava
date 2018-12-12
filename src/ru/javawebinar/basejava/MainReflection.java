package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("dummy");
        Class<? extends Resume> resumeClass = r.getClass();
        Field field = resumeClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new uuid");
        System.out.println(r);

        // TODO invoke method r.toString via reflection
        Method toStringMethod = resumeClass.getMethod("toString");
        Object result = toStringMethod.invoke(r);
        System.out.println(result);

        Method[] methodsArray = resumeClass.getMethods();
        for (Method method : methodsArray) {
            if (method.getName().equals("toString")) {
                System.out.println(method.invoke(r));
            }
            if (method.getName().startsWith("toS") && method.getParameterTypes().length == 0 && method.getReturnType().getName().contains("String")) {
                System.out.println(method.invoke(r));
            }
        }
    }
}
