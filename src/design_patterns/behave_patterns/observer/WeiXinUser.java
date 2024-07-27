package design_patterns.behave_patterns.observer;

//具体观察者类
public class WeiXinUser implements Observer{

    private String name;

    public WeiXinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}
