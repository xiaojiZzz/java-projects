package design_patterns.behave_patterns.observer;

import java.util.ArrayList;
import java.util.List;

//具体主题角色类
public class SubscriptionSubject implements Subject {

    //定义一个集合，用来存储多个观察者对象
    private List<Observer> weiXinUserList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        weiXinUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weiXinUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weiXinUserList) {
            observer.update(message);
        }
    }
}
