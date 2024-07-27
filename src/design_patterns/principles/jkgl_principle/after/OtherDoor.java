package design_patterns.principles.jkgl_principle.after;

public class OtherDoor implements AntiTheft, WaterProof{

    @Override
    public void antiTheft() {
        System.out.println("防👨");
    }

    @Override
    public void waterProof() {
        System.out.println("防💧");
    }
}
