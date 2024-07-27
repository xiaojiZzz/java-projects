package design_patterns.behave_patterns.state.before;

@SuppressWarnings({"all"})
//电梯类，ILift的子实现类
public class Lift implements ILift {

    //声明一个记录当前电梯的状态
    private int state;

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void open() {
        switch (state) {
            case OPENING_STATE:
                //什么都不做
                break;
            case CLOSING_STATE:
                System.out.println("电梯打开了。。。");
                setState(OPENING_STATE);
                break;
            case RUNNING_STATE:
                //什么都不做
                break;
            case STOPPING_STATE:
                System.out.println("电梯打开了。。。");
                setState(OPENING_STATE);
                break;
        }
    }

    @Override
    public void close() {
        switch (state) {
            case OPENING_STATE:
                System.out.println("电梯关闭了。。。");
                setState(CLOSING_STATE);
                break;
            case CLOSING_STATE:
                //什么都不做
                break;
            case RUNNING_STATE:
                //什么都不做
                break;
            case STOPPING_STATE:
                //什么都不做
                break;
        }
    }

    @Override
    public void run() {
        switch (state) {
            case OPENING_STATE:
                //什么都不做
                break;
            case CLOSING_STATE:
                System.out.println("电梯开始运行。。。");
                setState(RUNNING_STATE);
                break;
            case RUNNING_STATE:
                //什么都不做
                break;
            case STOPPING_STATE:
                System.out.println("电梯开始运行。。。");
                setState(RUNNING_STATE);
                break;
        }
    }

    @Override
    public void stop() {
        switch (state) {
            case OPENING_STATE:
                //什么都不做
                break;
            case CLOSING_STATE:
                System.out.println("电梯停止了。。。");
                setState(STOPPING_STATE);
                break;
            case RUNNING_STATE:
                System.out.println("电梯停止了。。。");
                setState(STOPPING_STATE);
                break;
            case STOPPING_STATE:
                //什么都不做
                break;
        }
    }
}
