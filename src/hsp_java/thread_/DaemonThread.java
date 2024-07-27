package hsp_java.thread_;

public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonThread myDaemonThread = new MyDaemonThread();

        myDaemonThread.setDaemon(true); //设置子线程为守护线程
        myDaemonThread.start();

        for (int i = 1; i <= 10; i++) {
            System.out.println("宝强在辛苦地工作。。。");
            Thread.sleep(1000);
        }
    }

}

class MyDaemonThread extends Thread{ //守护线程是，当其他用户线程都不工作了，自己也就断开不工作
    @Override
    public void run() {
        for (; ;) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("马蓉和宋喆快乐聊天，哈哈哈~~~");
        }
    }
}