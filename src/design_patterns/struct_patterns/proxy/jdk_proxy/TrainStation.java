package design_patterns.struct_patterns.proxy.jdk_proxy;

//火车站类
public class TrainStation implements SellTickets {
    @Override
    public void sell() {
        System.out.println("火车站卖票");
    }

    @Override
    public void run() {
        System.out.println("跑起来");
    }
}
