package design_patterns.creator_patterns.factory.config_factory;

public class Client {
    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee("american");

        System.out.println(coffee.getName());
    }
}
