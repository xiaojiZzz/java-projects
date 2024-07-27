package hsp_java.extends_.computer;

public class Pc extends Computer {
    private String brand;

    public Pc(String cpu, int memory, int disk, String brand) {
        super(cpu, memory, disk);
        this.brand = brand;
        this.setBrand("IBM");
        System.out.println();
    }

    public String getBrand() {
        System.out.println();
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //    public void printInfo() {
//        System.out.println("PC的信息为");
//        System.out.println(getDetails() + " brand=" + getBrand());
//    }
    public void getDetails(int i) {
        System.out.println("cpu=" + getCpu() + " memory=" + getMemory() + " disk=" + getDisk() + " brand=" + brand);
    }
}
