package java_learning;

public class Constructor {
    public static void main(String[] args) {
        Person p1 = new Person("Smith", 80);
        System.out.println("p1的hashcode值是" + p1.hashCode());
        System.out.println(p1.name + "的年龄是" + p1.age);
        Person p2 = new Person();
        System.out.println("p2的hashcode值是" + p2.hashCode());
        System.out.println(p2.name);
        System.out.println(p2.age);
//        实际参数列表和形式参数列表长度不同（除非构造无参构造器）
    }
    public int sayOk() {
        System.out.println("ok");
        return 2;
    }
}

class Person {
    String name;
    int age;
    //    创建构造器
//    public Person(String pName, int pAge) {
//        System.out.println("构造器被调用");
//        name = pName;
//        age = pAge;
//    }
//    引出 this
    public Person(String name, int age) {
        this(name);
        sayOk();
        System.out.println("构造器被调用");
        this.name = name;  //this.name 就是当前对象的属性name
        this.age = age;  //this.age 就是当前对象的属性age
        System.out.println("this的hashcode是" + this.hashCode());
    }

    public Person(String name) {
        this();
        System.out.println("构造器被调用");
//        this.name = name;  //this.name 就是当前对象的属性name
//        this.age = age;  //this.age 就是当前对象的属性age
        System.out.println("this的hashcode是" + this.hashCode());
    }

    public Person() {
//        重载（构造无参构造器）
        age = 18;
        name = "xiaoji";
    }
    public int sayOk() {
        System.out.println("ok");
        return 2;
    }
}
