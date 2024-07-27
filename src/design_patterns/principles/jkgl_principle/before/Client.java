package design_patterns.principles.jkgl_principle.before;

//如果还有一种门，只有防盗和防水的功能，如果直接实现SafetyDoor接口，就多加了一个防火的功能
public class Client {
    public static void main(String[] args) {
        HeimaSafetyDoor door = new HeimaSafetyDoor();
        door.antiTheft();
        door.fireProof();
        door.waterproof();
    }
}
