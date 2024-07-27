package design_patterns.struct_patterns.proxy.jdk_proxy;

public class Client {
    public static void main(String[] args) {
        //获取代理对象
        //创建代理工厂对象
        ProxyFactory proxyFactory = new ProxyFactory();
        //使用proxyFactory对象的方法获取代理对象
        SellTickets proxyObject = proxyFactory.getProxyObject();
        //调用卖票的方法
        proxyObject.run();
    }
}
