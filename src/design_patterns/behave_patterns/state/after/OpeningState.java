package design_patterns.behave_patterns.state.after;

//电梯开启状态
public class OpeningState extends LiftState{
    @Override
    public void open() {
        System.out.println("电梯开启。。。");
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.CLOSING_STATE);
        super.context.close();
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}
