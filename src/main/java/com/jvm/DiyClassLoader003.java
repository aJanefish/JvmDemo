package com.jvm;

import java.io.*;

//自定义ClassLoader003
public class DiyClassLoader003 extends ClassLoader {
    static {
        System.out.println("static DiyClassLoader003");
    }

    //public static final String driver = "test\\";
    public static final String driver = "target\\classes\\com\\bean\\";
    public static final String fileType = ".class";

    public Class findClass(String name) {
        show("name:" + name);
        byte[] data = loadClassData(name);
        show("data:" + data.length);
        return defineClass(data, 0, data.length);
    }

    public byte[] loadClassData(String name) {
        FileInputStream fis = null;
        byte[] data = null;
        try {
            File file = new File(driver + name + fileType);
            show(file.getAbsolutePath());
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch = 0;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("loadClassData-IOException");
        }
        return data;
    }

    private void show(String log) {
        System.out.println("DiyClassLoader003--: " + log);
    }
}