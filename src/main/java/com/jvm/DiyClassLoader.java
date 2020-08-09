package com.jvm;

import java.io.*;

public class DiyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            show("fileName:" + fileName);
            //InputStream is = getClass().getResourceAsStream(fileName);
            Class<? extends DiyClassLoader> aClass = getClass();
            show("aClass:" + aClass);
            InputStream is = aClass.getResourceAsStream(fileName);
            show("is:" + is);
            if (is == null) {
                return super.loadClass(name);
            }

            show("is.available():" + is.available());
            byte[] b = new byte[is.available()];
            is.read(b, 0, b.length);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    private void show(String log) {
        System.out.println("DiyClassLoader--: " + log);
    }

}
