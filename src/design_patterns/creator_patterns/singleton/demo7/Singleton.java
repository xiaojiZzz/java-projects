package design_patterns.creator_patterns.singleton.demo7;

import java.io.Serializable;

//单例模式：懒汉式（静态内部类方式）
//由于 JVM 在加载外部类的过程中, 是不会加载静态内部类的, 只有内部类的属性/方法被调用时才会被加载, 并初始化其静态属性。
//静态属性由于被 static 修饰，保证只被实例化一次，并且严格保证实例化顺序。
public class Singleton implements Serializable {

    //私有构造方法
    private Singleton() {
    }

    //定义一个静态内部类
    private static class SingletonHolder {
        //在内部类中声明并初始化外部类的对象
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //解决反序列话破坏单例模式:当进行反序列化时，会自动调用该方法（名字必须是readResolve），将方法的返回值直接返回
    public Object readResolve() {
        return SingletonHolder.INSTANCE;
    }
}
