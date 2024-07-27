package design_patterns.creator_patterns.factory.abstract_factorty;

public class Client {
    public static void main(String[] args) {
        //创建的是意大利风味的甜品工厂
        ItalyDessertFactory factory = new ItalyDessertFactory();
        //获取拿铁咖啡和提拉米苏甜品
        Coffee coffee = factory.createCoffee();
        Dessert dessert = factory.createDessert();

        System.out.println(coffee.getName());
        dessert.show();
    }
}
