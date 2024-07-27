package ccf_csp._2022._09;

import java.util.Arrays;
import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[] c = new int[n + 1];
        Arrays.fill(c, 1);
        for (int i = 0; i < n; i++) {
            a[i + 1] = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                c[i] *= a[j];
            }
        }
        for (int i = 1; i <= n; i++) {
            int t = m % c[i];
            b[i] = t / c[i -1];
            m -= t;
            System.out.print(b[i] + " ");
        }
    }
}
