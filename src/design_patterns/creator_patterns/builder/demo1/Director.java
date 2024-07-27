package design_patterns.creator_patterns.builder.demo1;

public class Director {

    //声明Builder类型的变量
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    //组装自行车的方法
    public Bike construct() {
        builder.buildFrame();
        builder.buildSeat();
        return builder.createBike();
    }
}
