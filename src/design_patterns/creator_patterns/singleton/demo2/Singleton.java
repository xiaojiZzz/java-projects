package design_patterns.creator_patterns.singleton.demo2;

//单列模式：饿汉式（静态代码块）
public class Singleton {
    //1.声明Singleton类型的变量
    private static Singleton instance;
    //2.在代码块中给instance赋值
    static {
        instance = new Singleton();
    }
    //3.私有构造器
    private Singleton() {}
    //4.对外提供获取该类对象的方法
    public static Singleton getInstance() {
        return instance;
    }
}
