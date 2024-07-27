package ccf_csp._2023._09;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[][] ope = new int[n][2];
        int[][] ans = new int[m][2];
        for (int i = 0; i < n; i++) {
            ope[i][0] = scanner.nextInt();
            ope[i][1] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt(), b = scanner.nextInt();
            for (int j = 0; j < n; j++) {
                a += ope[j][0];
                b += ope[j][1];
            }
            ans[i][0] = a;
            ans[i][1] = b;
        }
        for (int i = 0; i < m; i++) {
            System.out.println(ans[i][0] + " " + ans[i][1]);
        }
    }
}
