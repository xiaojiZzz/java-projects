package ccf_csp._2023._09;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[] ope1 = new int[n + 1];
        double[] ope2 = new double[n + 1];
        double[][] ans = new double[m][2];
        for (int i = 1; i <= n; i++) {
            ope1[i] = scanner.nextInt();
            ope2[i] = scanner.nextDouble();
        }
        for (int s = 0; s < m; s++) {
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            double x = scanner.nextLong();
            double y = scanner.nextLong();
            for (int k = i; k <= j; k++) {
                int a = ope1[k];
                double b = ope2[k];
                if (a == 1) {
                    x *= b;
                    y *= b;
                } else {
                    double tmp = x;
                    x = x * Math.cos(b) - y * Math.sin(b);
                    y = tmp * Math.sin(b) + y * Math.cos(b);
                }
            }
            ans[s][0] = x;
            ans[s][1] = y;
        }
        for (int i = 0; i < m; i++) {
            System.out.printf("%.3f", ans[i][0]);
            System.out.print(" ");
            System.out.printf("%.3f%n", ans[i][1]);
        }
    }
}
