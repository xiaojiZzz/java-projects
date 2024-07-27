package design_patterns.struct_patterns.proxy.static_proxy;

public class Client {
    public static void main(String[] args) {

        //创建代售点对象
        ProxyPoint proxyPoint = new ProxyPoint();
        //调用方法进行买票
        proxyPoint.sell();
    }
}
