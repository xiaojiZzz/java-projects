package design_patterns.struct_patterns.decorator;

public class Client {
    public static void main(String[] args) {

        //点一份炒饭
        FastFood friedRice = new FriedRice();
        System.out.println(friedRice.getDesc() + " " + friedRice.cost() + "元");

        System.out.println("====================");

        //在上面的炒饭里加一个鸡蛋
        friedRice = new Egg(friedRice);
        System.out.println(friedRice.getDesc() + " " + friedRice.cost() + "元");
    }
}
