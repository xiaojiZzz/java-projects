package design_patterns.struct_patterns.proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//代理对象工厂，用来获取代理对象
//动态代理（没有接口的情况下）
//jdk1.8可用，jdk18不好用
public class ProxyFactory implements MethodInterceptor {

    private TrainStation station = new TrainStation();

    public TrainStation getProxyObject() {
        //创建Enhancer对象,类似jdk代理中的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置父类的字节码对象
        enhancer.setSuperclass(TrainStation.class);
        //设置回调函数
        enhancer.setCallback(this);
        //创建代理对象
        TrainStation proxyObject = (TrainStation) enhancer.create();
        return proxyObject;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        System.out.println("方法执行");
        System.out.println("代售点收取一定的费用");
        Object obj = method.invoke(station, objects);
        return obj;
    }
}
