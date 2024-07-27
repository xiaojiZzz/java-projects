package hsp_java.poly;

public class Poly01 {
    public static void main(String[] args) {
        Master xiaoji = new Master("xiaoji");
        Dog dog = new Dog("maodou");
        Bone bone = new Bone("骨头");

        xiaoji.feed(dog, bone);

        Cat cat = new Cat("gugu");
        Fish fish = new Fish("xiaoyu");

        xiaoji.feed(cat, fish);

    }
}