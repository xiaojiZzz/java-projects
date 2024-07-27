package design_patterns.principles.jkgl_principle.after;

//接口隔离原则：客户端不应该被迫依赖于它不使用的方法；一个类对另一个类的依赖应该建立在最小的接口上。
public class Client {
    public static void main(String[] args) {
        HeimaSafetyDoor door = new HeimaSafetyDoor();
        door.antiTheft();
        door.fireProof();
        door.waterProof();

        System.out.println("==============");
        OtherDoor otherDoor = new OtherDoor();
        otherDoor.antiTheft();
        otherDoor.waterProof();
    }
}
