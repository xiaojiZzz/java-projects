package design_patterns.creator_patterns.factory.abstract_factorty;

//美式风味的甜品工厂,可以生产美式咖啡和抹茶慕斯
public class AmericanDessertFactory implements DessertFactory{
    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new MatchaMousse();
    }
}
