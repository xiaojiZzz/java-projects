package ccf_csp._2023._05;

import java.util.Arrays;
import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        int[][] Q = new int[n][d];
        int[][] K = new int[n][d];
        int[][] V = new int[n][d];
        long[] tmp = new long[n];
        long[] res = new long[n];
        int[] W = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d; j++) {
                Q[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d; j++) {
                K[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d; j++) {
                V[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            W[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(tmp, 0);
            Arrays.fill(res, 0);
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < d; k++) {
                    tmp[j] += Q[i][k] * K[j][k];
                }
            }
            for (int j = 0; j < d; j++) {
                for (int k = 0; k < n; k++) {
                    res[j] += tmp[k] * V[k][j];
                }
            }
            for (int j = 0; j < d; j++) {
                System.out.print(res[j] * W[i]);
                if (j != d - 1) {
                    System.out.print(" ");
                } else if (j == d - 1 && i != n - 1) {
                    System.out.println();
                }
            }
        }
    }
}
