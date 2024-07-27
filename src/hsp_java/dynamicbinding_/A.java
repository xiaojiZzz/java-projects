package hsp_java.dynamicbinding_;

public class A {
    public int i = 10;

    public int sum() {
        return get1() + 10;
//        return this.get1() + 10;
    }

    public int sum1() {
        return i + 10;
    }

    public int get1() {
        return i;
    }
}
