package design_patterns.creator_patterns.factory.static_factory;

public class SimpleCoffeeFactory {

    public static Coffee createCoffee(String type) {

        Coffee coffee = null;
        if ("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new LatteCoffee();
        } else {
            throw new RuntimeException("对不起，你点的咖啡不存在");
        }

        return coffee;
    }
}
