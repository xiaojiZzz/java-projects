package design_patterns.creator_patterns.factory.static_factory;

public class CoffeeStore {
    public Coffee orderCoffee(String type) {

//        SimpleCoffeeFactory factory = new SimpleCoffeeFactory();
//        //调用生产咖啡的方法
//        Coffee coffee = factory.createCoffee(type);
        Coffee coffee = SimpleCoffeeFactory.createCoffee(type);

        //加配料
        coffee.addMilk();
        coffee.addSugar();

        return coffee;
    }
}
