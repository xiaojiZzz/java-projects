package design_patterns.creator_patterns.factory.factory_method;

//拿铁咖啡工厂对象，专门用来生产拿铁咖啡
public class LatteCoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
