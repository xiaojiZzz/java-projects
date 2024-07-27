package design_patterns.creator_patterns.factory.factory_method;

//抽象工厂角色
public interface CoffeeFactory {

    //创建咖啡对象的方法
    Coffee createCoffee();
}

