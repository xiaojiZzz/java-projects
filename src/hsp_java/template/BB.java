package hsp_java.template;

public class BB extends Template{
    @Override
    public void job() {
        long num = 0;
        for (int i = 1; i <= 8000000; i++) {
            num += i;
        }
    }
}
