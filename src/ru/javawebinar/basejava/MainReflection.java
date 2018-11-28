package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new uuid");
        System.out.println(r);

        // TODO invoke method r.toString via reflection
        Method toStringMethod = r.getClass().getMethod("toString");
        System.out.println(toStringMethod.invoke(r, null));

        Method[] methodsArray = r.getClass().getMethods();
        for (Method method : methodsArray) {
            if (method.getName().equals("toString")) {
                System.out.println(method.invoke(r, null));
            }
            if (method.getName().startsWith("toS") && method.getParameterTypes().length == 0 && method.getReturnType().getName().contains("String")){
                System.out.println(method.invoke(r, null));
            }
        }
    }
}
