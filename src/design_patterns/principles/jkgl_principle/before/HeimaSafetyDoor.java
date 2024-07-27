package design_patterns.principles.jkgl_principle.before;

public class HeimaSafetyDoor implements SafetyDoor{

    @Override
    public void antiTheft() {
        System.out.println("防盗");
    }

    @Override
    public void fireProof() {
        System.out.println("防火");
    }

    @Override
    public void waterproof() {
        System.out.println("防水");
    }
}
