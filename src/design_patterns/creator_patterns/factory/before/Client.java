package design_patterns.creator_patterns.factory.before;

public class Client {
    public static void main(String[] args) {
        //1.创建咖啡店类
        CoffeeStore coffeeStore = new CoffeeStore();
        //2.点咖啡
        Coffee coffee = coffeeStore.orderCoffee("latte");

        System.out.println(coffee.getName());
    }
}
