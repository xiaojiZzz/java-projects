package hsp_java.thread_.sell_ticket;

//互斥锁
//使用 synchronized 来解决同一时刻有多个线程对资源的访问
//同步方法（非静态的）的锁可以是this，也可以是其他对象（要求是同一个对象），由这个对象的同步标识位来判断是不是可以使用这个方法
//同步方法(静态的）的锁为当前类本身。
//优先使用同步代码块而不是同步方法，因为代码块的限制范围小，效率高
@SuppressWarnings({"all"})
public class SellTicket {
    public static void main(String[] args) {

//        测试
        SellTicket01 sellTicket01 = new SellTicket01();
        SellTicket01 sellTicket02 = new SellTicket01();
        SellTicket01 sellTicket03 = new SellTicket01();

        sellTicket01.start();
        sellTicket02.start();
        sellTicket03.start();

//        SellTicket02 sellTicket02 = new SellTicket02();
//        Thread thread1 = new Thread(sellTicket02);
//        Thread thread2 = new Thread(sellTicket02);
//        Thread thread3 = new Thread(sellTicket02);
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
    }
}

//继承的方式,使用 synchronized 实现线程同步（方法上）
@SuppressWarnings({"all"})
class SellTicket01 extends Thread {

    private static int ticketNum = 100;
    private static boolean loop = true;

    public static void sell() { //同步方法，在同一个时刻只能有一个线程执行 sell 方法,这里换成了 静态方法里的同步代码块

        synchronized (SellTicket01.class) {
            if (ticketNum <= 0) {
                System.out.println("售票结束。。。");
                loop = false;
                return;
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票" +
                    "，剩余 " + (--ticketNum) + " 张票。");
        }
    }

    @Override
    public void run() {

        while (loop) {
            sell();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//实现接口的方式,使用 synchronized 实现线程同步（代码块）
@SuppressWarnings({"all"})
class SellTicket02 implements Runnable {

    private int ticketNum = 100; //共享资源 可以不需要 static
    private boolean loop = true;

    public void sell() {
        synchronized (this) {
            if (ticketNum <= 0) {
                System.out.println("售票结束。。。");
                loop = false;
                return;
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票" +
                    "，剩余 " + (--ticketNum) + " 张票。");
        }
    }

    @Override
    public void run() {

        while (loop) {
            sell();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}