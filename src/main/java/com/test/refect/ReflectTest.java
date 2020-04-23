package com.test.refect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;

import com.sun.deploy.util.ReflectionUtil;
import com.test.Subject;

public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException {
        Subject subject = new Subject("yuwen", 90);
        Field field = ReflectionUtils.findField(subject.getClass(), "sname");
        ReflectionUtils.makeAccessible(field);
        String value = (String) ReflectionUtils.getField(field, subject);
        System.out.println(value);
        ReflectionUtils.setField(field, subject, "shuxue");
        System.out.println((String) ReflectionUtils.getField(field, subject));

        Method method = ReflectionUtils.findMethod(Subject.class, "printself");
        ReflectionUtils.invokeMethod(method, subject);

        Method method1 = ReflectionUtils.findMethod(Subject.class, "printself", String.class);
        ReflectionUtils.invokeMethod(method1, subject, "567890");


    }

}
