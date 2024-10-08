package design_patterns.behave_patterns.state.after;

public class Client {
    public static void main(String[] args) {
        //创建环境角色对象
        Context context = new Context();
        //设置当前电梯状态
        context.setLiftState(new RunningState());

        context.open();
        context.close();
        context.run();
        context.stop();

        System.out.println("-----------");

        context.setLiftState(new ClosingState());

        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
