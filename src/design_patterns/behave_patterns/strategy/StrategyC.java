package design_patterns.behave_patterns.strategy;

//具体策略类，封装方法
public class StrategyC implements Strategy{
    @Override
    public void show() {
        System.out.println("满1000加一元任选200以内的商品一件");
    }
}
