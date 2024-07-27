package design_patterns.creator_patterns.singleton.demo8;

import java.io.Serializable;

//单例模式：懒汉式（静态内部类方式）
//由于 JVM 在加载外部类的过程中, 是不会加载静态内部类的, 只有内部类的属性/方法被调用时才会被加载, 并初始化其静态属性。
//静态属性由于被 static 修饰，保证只被实例化一次，并且严格保证实例化顺序。
public class Singleton implements Serializable {

    private static boolean flag = false;

    //私有构造方法
    private Singleton() {
        synchronized (Singleton.class) {

            //判断flag的值是否是true，如果是true，说明不是第一次访问，直接抛一个异常，如果是false，说明第一次访问
            if (flag) {
                throw new RuntimeException("不能创建多个对象");
            } else {
                flag = true;
            }
        }
    }

    //定义一个静态内部类
    private static class SingletonHolder {
        //在内部类中声明并初始化外部类的对象
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
