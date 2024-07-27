package hsp_java.poly;

public class Master {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Master(String name) {
        this.name = name;
    }

//    用多态来解决喂食问题
//    animal编译类型是Animal，可以接收Animal子类
    public void feed(Animal animal, Food food) {
        System.out.println("主人 " + name + " 给 " + animal.getName() + " 吃 " + food.getName());
    }
////    主人给小狗喂食
//    public void feed(Dog dog, Bone bone) {
//        System.out.println("主人 " + name + " 给 " + dog.getName() + " 吃 " + bone.getName());
//    }
////    主人给小猫喂食
//    public void feed(Cat cat, Fish fish) {
//        System.out.println("主人 " + name + " 给 " + cat.getName() + " 吃 " + fish.getName());
//    }
}
