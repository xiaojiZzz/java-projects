package hsp_java.thread_;

@SuppressWarnings({"all"})
public class DeadLock_ {
    public static void main(String[] args) {
        DeadLockDemo A = new DeadLockDemo(true);
        DeadLockDemo B = new DeadLockDemo(false);
        A.start();
        B.start();
    }
}


@SuppressWarnings({"all"})
class DeadLockDemo extends Thread {
    static Object o1 = new Object();
    static Object o2 = new Object();
    private boolean flag;

    public DeadLockDemo(boolean flag) {
        this.flag = flag;
    }


    @Override
    public void run() {
        if (flag) {
            synchronized (o1) { //对象互斥锁，下面就是同步代码
                System.out.println(Thread.currentThread().getName() + "进入1");
                synchronized (o2) { //获得对象的监视权
                    System.out.println(Thread.currentThread().getName() + "进入2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "进入3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "进入4");
                }
            }
        }
    }
}