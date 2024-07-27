package ccf_csp._2018._09;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] prices = new int[n];
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        res[0] = (prices[0] + prices[1]) / 2;
        for (int i = 1; i < n - 1; i++) {
            res[i] = (prices[i - 1] + prices[i + 1] + prices[i]) / 3;
        }
        res[n - 1] = (prices[n - 2] + prices[n - 1]) / 2;
        for (int i = 0; i < n; i++) {
            System.out.print(res[i]);
            System.out.print(" ");
        }
    }
}
