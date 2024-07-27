package ccf_csp._2016._09;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        int max = 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(Math.abs(prices[i] - prices[i - 1]), max);
        }
        System.out.println(max);
    }
}
