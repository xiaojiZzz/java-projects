package ccf_csp._2013._12;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        String[] split = str.split("-");
        String s = "";
        for (int i = 0; i < split.length - 1; i++) {
            s += split[i];
        }
        char[] chars = s.toCharArray();
        int temp = 0;
        for (int i = 1; i < 10; i++) {
            temp += (chars[i - 1] - 48) * i;
        }
        int g = temp % 11;
        if (g != 10) {
            if (("" + g).equals(split[3])) {
                System.out.println("Right");
            } else {
                String res = "";
                for (int i = 0; i < 3; i++) {
                    res += split[i];
                    res += "-";
                }
                res += g;
                System.out.println(res);
            }
        } else {
            if ("X".equals(split[3])) {
                System.out.println("Right");
            } else {
                String res = "";
                for (int i = 0; i < 3; i++) {
                    res += split[i];
                    res += "-";
                }
                res += "X";
                System.out.println(res);
            }
        }
    }
}
