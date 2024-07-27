package design_patterns.creator_patterns.singleton.demo8;

import java.lang.reflect.Constructor;

//测试用反射方式破坏单例模式
//解决方法：加flag，在第二次创建对象时，抛异常
public class Client {
    public static void main(String[] args) throws Exception {

        //1.获取Singleton的字节码对象
        Class<Singleton> cls = Singleton.class;
        //2.获取无参构造方法对象
        Constructor<Singleton> cons = cls.getDeclaredConstructor();
        //3.取消访问检查
        cons.setAccessible(true);
        //4.创建Singleton对象
        Singleton singleton = cons.newInstance();
        Singleton singleton1 = cons.newInstance();

        System.out.println(singleton == singleton1);
    }
}
