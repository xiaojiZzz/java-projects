package design_patterns.behave_patterns.strategy;

//促销员，环境类
public class SalesMan {

    //聚合策略类对象
    private Strategy strategy;

    public SalesMan(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    //由促销员展示促销商品给顾客
    public void salesManShow() {
        strategy.show();
    }
}
