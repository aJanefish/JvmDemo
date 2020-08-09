package com.bean;

public class Person {
    public Person() {

    }

    public void show(){
        System.out.println("Hello!!! MyClassLoader is "+getClass().getClassLoader());
    }
}
