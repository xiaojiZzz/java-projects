package hsp_java.template;

public class AA extends Template{
    @Override
    public void job() {
        long num = 0;
        for (int i = 1; i <= 1000000; i++) {
            num += i;
        }
    }
}
