package hsp_java.thread_;

/**
 * 实现 Runnable 接口方式更加适合多个线程共享一个资源的情况，
 * 并且避免了单继承的限制。
 */
public class Thread_Runnable {
    public static void main(String[] args) {
        Dog dog = new Dog();
//        dog.start();  没有此方法
        Thread thread = new Thread(dog); // dog对象需要实现 Runnable 接口；使用了静态代理模式（设计模式）
        thread.start();
//        dog.run(); //共执行10次，因为count是实例变量
    }
}


class Dog implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("小狗汪汪叫" + " " + (++count) + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 10) {
                break;
            }
        }
    }
}