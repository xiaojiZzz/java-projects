package hsp_java.reflection_;

import java.lang.reflect.Method;

@SuppressWarnings({"all"})
//对三种调用对象方法性能的比较
public class ReflectionGap {
    public static void main(String[] args) throws Exception {
        m1();
        m2();
        m3();
    }

    //传统方法调用方法
    public static void m1() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            cat.hello();
        }
        long end = System.currentTimeMillis();
        System.out.println("m1()耗时：" + (end - start));
    }

    //反射机制调用方法
    public static void m2() throws Exception {
        Class cls = Class.forName("hsp_java.reflection_.Cat");
        Object o = cls.getDeclaredConstructor().newInstance();
        Method hello = cls.getMethod("hello");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            hello.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m2()耗时：" + (end - start));
    }

    //优化反射机制调用方法(关闭访问检查)
    public static void m3() throws Exception {
        Class cls = Class.forName("hsp_java.reflection_.Cat");
        Object o = cls.getDeclaredConstructor().newInstance();
        Method hello = cls.getMethod("hello");
        hello.setAccessible(true); //在反射调用方法时，关闭访问检查
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            hello.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m2()耗时：" + (end - start));
    }
}
