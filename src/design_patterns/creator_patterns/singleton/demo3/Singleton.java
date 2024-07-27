package design_patterns.creator_patterns.singleton.demo3;

//单例模式：懒汉式（线程不安全）加上synchronized变为线程安全
public class Singleton {

    //只声明变量，没有赋值
    private static Singleton instance;

    //私有构造器
    private Singleton() {}

    //对外提供使用对象的方式
    public static synchronized Singleton getInstance() {
        //判断instance是否为null，如果为null，说明还没有创建Singleton类的对象
        if (instance == null) { //但是是线程不安全的，除非现在加上synchronized
            instance = new Singleton();
        }
        return instance;
    }
}
