package hsp_java.reflection_;

import java.io.Serializable;

@SuppressWarnings({"all"})
//演示哪些类有Class对象
public class AllTypeClass {
    public static void main(String[] args) {

        Class<String> cls1 = String.class; //外部类
        Class<Serializable> cls2 = Serializable.class; //接口
        Class<Integer[]> cls3 = Integer[].class; //数组
        Class<float[][]> cls4 = float[][].class; //二维数组
        Class<Deprecated> cls5 = Deprecated.class; //注解
        Class<Thread.State> cls6 = Thread.State.class; //枚举
        Class<Long> cls7 = long.class; //基本数据类型
        Class<Void> cls8 = void.class; //返回类型 void数据类型
        Class<Class> cls9 = Class.class; //Class类

        System.out.println(cls1);
        System.out.println(cls2);
        System.out.println(cls3);
        System.out.println(cls4);
        System.out.println(cls5);
        System.out.println(cls6);
        System.out.println(cls7);
        System.out.println(cls8);
        System.out.println(cls9);
    }
}
