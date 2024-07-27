package hsp_java.exception_;

public class Exception01 {
    public static void main(String[] args) throws Exception {
        int a = 10;
        int b = 0;
        int res;
        AAAA aaaa = new AAAA();
        aaaa = null;
        try {
//            System.out.println(aaaa.getName());
            res = a / b;
        } catch (NullPointerException e) {
            System.out.println("发生了空指针异常");
        } catch (ArithmeticException e) {
            System.out.println("发生了算数异常");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getSuppressed());
            System.out.println("出现异常的原因是：" + e.getMessage());
        } finally {
            System.out.println("运行结束");
        }
    }
}

class AAAA {
    String name = "jack";

    public String getName() {
        return name;
    }
}
