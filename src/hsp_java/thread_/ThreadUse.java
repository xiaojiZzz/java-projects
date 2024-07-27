package hsp_java.thread_;

//进程->main线程->Thread-0子线程
public class ThreadUse {
    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();
        cat.start(); //启动线程 如果是 cat.run() 则不会开一个子线程，一直都是main线程
        //说明：当main线程启动一个子线程 Thread-0，主线程不会阻塞
        //这时候，主线程和子线程交替执行
        System.out.println("主线程名字=" + Thread.currentThread().getName()); //main
        for (int i = 1; i <= 60; i++) {
            System.out.println("主线程在执行中。i=" + i);
            Thread.sleep(1000);
        }
    }
}

class Cat extends Thread {

    int times = 0;

    @Override
    public void run() {

        while (true) {
            //该线程每隔一秒，在控制台上输出"喵喵，我是小猫咪"
            System.out.println("喵喵，我是小猫咪" + (++times) + " 线程名=" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { //try-catch 是为了防止睡死
                e.printStackTrace();
            }
            if (times == 80) {
                break;
            }
        }
    }
}
