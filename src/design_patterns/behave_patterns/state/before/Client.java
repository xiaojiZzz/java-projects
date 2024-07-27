package design_patterns.behave_patterns.state.before;

public class Client {
    public static void main(String[] args) {
        //创建对象
        Lift lift = new Lift();
        //设置状态
        lift.setState(ILift.OPENING_STATE);
        //打开
        lift.open();
        lift.close();
        lift.run();
        lift.stop();

        System.out.println("+++++++++++++++");

        //测试
        lift.setState(ILift.RUNNING_STATE);
        //打开
        lift.open();
        lift.close();
        lift.run();
        lift.stop();
    }
}
