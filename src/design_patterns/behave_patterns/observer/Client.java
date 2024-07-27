package design_patterns.behave_patterns.observer;

public class Client {
    public static void main(String[] args) {
        //创建公众号对象
        SubscriptionSubject subject = new SubscriptionSubject();
        //订阅公众号
        subject.attach(new WeiXinUser("孙悟空"));
        subject.attach(new WeiXinUser("猪八戒"));
        subject.attach(new WeiXinUser("沙和尚"));
        //公众号更新，发出消息给订阅者（观察者对象）
        subject.notify("三个傻子，师傅被妖怪又抓走了");
    }
}
