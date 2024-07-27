package hsp_java.extends_.computer;

public class Extends_drive {
    public static void main(String[] args) {
        Pc pc = new Pc("Intel", 16, 512, "IBM");
//        pc.printInfo();
        pc.getDetails(1);
        System.out.println(pc);
//        System.gc();
    }
}
