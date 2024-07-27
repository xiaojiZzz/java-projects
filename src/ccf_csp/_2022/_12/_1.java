package ccf_csp._2022._12;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double i = scanner.nextDouble();
        double sum = 0;
        for (int j = 0; j <= n; j++) {
            int money = scanner.nextInt();
            sum += money * Math.pow((1 + i), -j);
        }
        System.out.println(sum);
    }
}
