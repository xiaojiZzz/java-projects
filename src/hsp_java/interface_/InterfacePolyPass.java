package hsp_java.interface_;

/**
 * 演示接口多态传递
 */
public class InterfacePolyPass {
    public static void main(String[] args) {
        IH ih = new Teacher();
        IG ig = new Teacher();
    }
}

interface IH extends IG{

}

interface IG {

}

class Teacher implements IH {

}