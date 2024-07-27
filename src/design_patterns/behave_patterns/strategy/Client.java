package design_patterns.behave_patterns.strategy;

public class Client {
    public static void main(String[] args) {

        //春季来了，使用春季促销活动
        SalesMan salesMan = new SalesMan(new StrategyA());
        //展示促销活动
        salesMan.salesManShow();

        System.out.println("=================");

        //国庆节到了，使用国庆节促销活动
        salesMan.setStrategy(new StrategyB());
        //展示促销活动
        salesMan.salesManShow();
    }
}
