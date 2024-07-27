package hsp_java.thread_;

public class ProxyMode {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();
    }
}

class Animal {

}


class Tiger extends Animal implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()); //只是模拟，没有调用底层的处理器等资源
        System.out.println("老虎嗷嗷叫。。。");
    }
}


//线程代理类,模拟了一个极简的 Thread 类
class ThreadProxy implements Runnable {

    private Runnable target = null;

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    public void start() {
        start0();
    }

    public void start0() {
        run();
    }
}