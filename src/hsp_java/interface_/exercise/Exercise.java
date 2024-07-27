package hsp_java.interface_.exercise;

public class Exercise {
    public static void main(String[] args) {
        Person tang = new Person("唐僧", VehiclesFactory.getHorse());
        tang.common();
        tang.passRiver();
        tang.passRiver();
        tang.common();
        tang.common();
        tang.common();
        tang.passRiver();
    }
}
