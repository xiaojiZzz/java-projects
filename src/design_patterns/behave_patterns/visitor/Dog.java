package design_patterns.behave_patterns.visitor;

//具体元素角色类，宠物狗
public class Dog implements Animal{
    @Override
    public void accept(Person person) {
        person.feed(this);
        System.out.println("好好吃，汪汪汪。。。");
    }
}
