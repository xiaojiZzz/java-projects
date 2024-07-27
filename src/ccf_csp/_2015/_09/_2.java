package ccf_csp._2015._09;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt();
        int d = scanner.nextInt();
        boolean is = isFeb(y);
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (is) {
            days[1] = 29;
        }
        int i = 0;
        for (; i < 12; i++) {
            if (d - days[i] <= 0) {
                break;
            }
            d -= days[i];
        }
        System.out.println(i + 1);
        System.out.print(d);
    }

    public static boolean isFeb(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }
}
