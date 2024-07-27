package design_patterns.behave_patterns.strategy;

//具体策略类，封装方法
public class StrategyB implements Strategy{
    @Override
    public void show() {
        System.out.println("满200送50");
    }
}
