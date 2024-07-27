package hsp_java.reflection_;

public class Cat {
    private String name = "xiaogu";
    public int age = 1;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public void hi() {
        System.out.println(name + " 说 hi～");
    }

    public void cry() {
        System.out.println(name + " 哭了");
    }

    public void hello() {}
}
