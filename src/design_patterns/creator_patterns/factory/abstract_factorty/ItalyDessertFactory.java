package design_patterns.creator_patterns.factory.abstract_factorty;

//意大利风味的甜品工厂，可以生产拿铁咖啡和提拉米苏
public class ItalyDessertFactory implements DessertFactory{
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new Tiramisu();
    }
}
