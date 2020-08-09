package com.jvm;

import java.io.*;

public class DiyClassLoader002 extends ClassLoader {
    private String path;
    private String classLoaderName;

    public DiyClassLoader002(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            show("name:" + name);
            byte[] b = loadClassData(name);
            show("b:" + b.length);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] loadClassData(String name) throws IOException {
        name = path + name + ".class";
        show(name);
        InputStream is = null;
        ByteArrayOutputStream outputStream = null;
        try {
            File file = new File(name);
            show(file.toString());
            is = new FileInputStream(file);
            outputStream = new ByteArrayOutputStream();
            int i = 0;
            while ((i = is.read()) != -1) {
                outputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (is != null) {
                is.close();
            }
        }

        return outputStream.toByteArray();
    }

    private void show(String log) {
        System.out.println("DiyClassLoader002--: " + log);
    }
}