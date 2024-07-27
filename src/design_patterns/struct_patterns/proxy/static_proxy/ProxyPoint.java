package design_patterns.struct_patterns.proxy.static_proxy;

//代售点(聚合火车站类）
public class ProxyPoint implements SellTickets{

    //声明火车站类对象
    private TrainStation trainStation = new TrainStation();
    @Override
    public void sell() {

        System.out.println("代售点收取一些服务费用");
        trainStation.sell();
    }
}
