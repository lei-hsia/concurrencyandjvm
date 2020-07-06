package com.lei.javase.interviews.mianshibaodian;

public class LoadClass {
    // JVM加载类: forName， loadClass方法的不同点
    public static void main(String[] args) throws ClassNotFoundException {
        // Class类, 加载class
        Class<?> someClass = Class.forName("Someclass");
        // = Class.forName("some", true, someClass.getClassLoader()); 默认是true，也就是解释和初始化

        // ClassLoader类，加载class
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // c = parent.loadClass(name, false); : 默认是false,也就是不resolve
        classLoader.loadClass("Someclass2");
    }

}

class Someclass { }