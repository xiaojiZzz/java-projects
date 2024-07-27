package design_patterns.behave_patterns.memento.black_box;

//备忘录对象管理对象
public class RoleStateCaretaker {

    //声明一个RoleStateMemento类型的变量
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

}
