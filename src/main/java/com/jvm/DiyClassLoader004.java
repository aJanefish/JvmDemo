package com.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//自定义ClassLoader003
public class DiyClassLoader004 extends ClassLoader {
    static {
        System.out.println("static DiyClassLoader004");
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        show("loadClass:" + name);
        return super.loadClass(name);
    }


    private void show(String log) {
        System.out.println("DiyClassLoader004--: " + log);
    }
}