package design_patterns.creator_patterns.singleton.demo4;

//单例模式：懒汉式（双重检查锁）调整加锁的时机
public class Singleton {

    private static volatile Singleton instance; //在多线程的情况下，可能会出现空指针问题，
    //出现问题的原因是JVM在实例化对象的时候会进行优化和指令重排序操作，所以要加上volatile，关键字可以保证可见性和有序性。

    private Singleton() {
    }

    public static Singleton getInstance() {
        //第一次判断，如果instance的值不为null，不需要抢占资源，直接返回对象
        if (instance == null) {
            synchronized (Singleton.class) {
                //第二次判断
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
