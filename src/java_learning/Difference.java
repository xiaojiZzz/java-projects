package java_learning;

public class Difference {
    public static void main(String[] args) {
        M m = new N();
        m.output();
        m.output2();
        N n = new N();
        n.output();
        n.output2();
    }
}

class M {
    public static void output() {
        System.out.println("M");
    }

    public void output2() {
        System.out.println("m");
    }
}

class N extends M {
    public static void output() {
        System.out.println("N");
    }

    public void output2() {
        System.out.println("n");
    }
}