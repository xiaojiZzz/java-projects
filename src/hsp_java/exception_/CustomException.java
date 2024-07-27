package hsp_java.exception_;

public class CustomException {
    public static void main(String[] args) {

        int age = 6;
        if (!(age >= 18 && age <= 120)) {
            throw new AgeException("年龄需要在 18-120 之间");
        }
        System.out.println("你的年龄范围正确");
    }
}

class AgeException extends RuntimeException {
    public AgeException(String message) {
        super(message);
    }
}
