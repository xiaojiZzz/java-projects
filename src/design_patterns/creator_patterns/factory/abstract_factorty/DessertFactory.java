package design_patterns.creator_patterns.factory.abstract_factorty;

public interface DessertFactory {
    //生产咖啡的功能
    Coffee createCoffee();
    //生产甜品的功能
    Dessert createDessert();
}
