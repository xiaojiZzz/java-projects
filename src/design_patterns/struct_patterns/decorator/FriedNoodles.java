package design_patterns.struct_patterns.decorator;

//炒面类，具体构件角色
public class FriedNoodles extends FastFood{

    public FriedNoodles() {
        super(12, "炒面");
    }

    @Override
    public float cost() {
        return getPrice();
    }
}
