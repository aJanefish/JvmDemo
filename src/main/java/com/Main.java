package com;


import com.bean.Person;
import com.jvm.DiyClassLoader;
import com.jvm.DiyClassLoader002;
import com.jvm.DiyClassLoader003;
import com.jvm.DiyClassLoader004;
import org.omg.PortableServer.THREAD_POLICY_ID;
import sun.dc.path.PathError;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    static{
        Thread.currentThread().setContextClassLoader(new DiyClassLoader004());
    }
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        show(classLoader.toString());
        //testClass();
        //testDiyClassLoader();
        //testDiyClassLoader002();
        testDiyClassLoader003();

    }

    //测试自定义ClassLoader003
    // 成功了
    private static void testDiyClassLoader003() {

        DiyClassLoader003 cl1 = new DiyClassLoader003();

        show("getSystemClassLoader:"+DiyClassLoader003.getSystemClassLoader());
        try {
            Class aClass = cl1.loadClass("Person");

            //show("isInstance:" + aClass.isInstance(new Person()));
            //show("isAssignableFrom:" + aClass.isAssignableFrom(Person.class));
            testClassLoader(aClass);
            Object object = aClass.newInstance();
            show(object.toString());
            show("object instanceof Person = " + (object instanceof Person));

            Method method = aClass.getDeclaredMethod("show");
            method.setAccessible(true);
            method.invoke(object);

            Person person = new Person();
            person.show();
            //show("person.equals(object):" + person.equals(object));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试DiyClassLoader002
    private static void testDiyClassLoader002() {
        DiyClassLoader002 classLoader002 = new DiyClassLoader002("D:/Study/JavaWod/JvmDemo/test/com/bean/", "testDiyClassLoader002");
        try {
            Class<?> aClass = classLoader002.loadClass("Person");

            Object object = aClass.newInstance();

            System.out.println(object);
            System.out.println(object.getClass());
            testClassLoader(object.getClass());
            System.out.println(object instanceof Person);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //测试DiyClassLoader
    private static void testDiyClassLoader() {
        DiyClassLoader diyClassLoader = new DiyClassLoader();
        try {
            Class<?> aClass = diyClassLoader.loadClass("com.bean.Person");

            Object object = aClass.newInstance();

            System.out.println(object);
            System.out.println(object.getClass());
            testClassLoader(object.getClass());
            System.out.println(object instanceof Person);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void testClass() {
        testClassLoader(System.class);
        testClassLoader(Main.class);
        testClassLoader(Object.class);
        testClassLoader(Math.class);
        testClassLoader(int.class);
        testClassLoader(Integer.class);
        testClassLoader(String.class);
    }

    private static void testClassLoader(Class mainClass) {
        System.out.println("------------------------------------testClassLoader start");
        ClassLoader classLoader = mainClass.getClassLoader();
        System.out.println(mainClass);
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println("------------------------------------testClassLoader end");
    }

    private static void show(Object log) {
        System.out.println("Main--: " + log);
    }
}
