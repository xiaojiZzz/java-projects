package hsp_java.enumeration_;

public class Enumeration02 {
    public static void main(String[] args) {
        System.out.println(Season2.SPRING.getName());
    }
}

enum Season2 {


//    public final static Season2 SPRING = new Season2("春天", "温暖");
//    public final static Season2 SUMMER = new Season2("夏天", "炎热");
//    public final static Season2 AUTUMN = new Season2("秋天", "凉爽");
//    public final static Season2 WINNER = new Season2("冬天", "寒冷");
    // 使用 enum 来实现枚举类
    // 1.使用关键字 enum 替代 class
    // 2.public final static Season2 SPRING = new Season("春天", "温暖");直接使用 SPRING("春天", "温暖")
    // 3.多个常量（对象），用,逗号隔开
    // 4.如果使用 enum 来实现枚举，常量需要写在最前面


    SPRING("春天", "温暖"), SUMMER("夏天", "炎热"),
    AUTUMN("秋天", "凉爽"), WINNER("冬天", "寒冷");

    private final String name;
    private final String desc;

    Season2(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}