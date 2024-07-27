package ccf_csp._2021._04;

import java.util.Scanner;

public class _2 {
    //空间换时间
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), L = scanner.nextInt(), r = scanner.nextInt(), t = scanner.nextInt();
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        int[][] cnt = new int[n][n];
        int[][] cnt2 = new int[n][n];
        double[][] res = new double[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = -r; k <= r; k++) {
                    if (((j + k) >= 0 && (j + k) < n)) {
                        B[i][j] += A[i][j + k];
                        cnt[i][j]++;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = -r; k <= r; k++) {
                    if ((i + k) >= 0 && (i + k) < n) {
                        res[i][j] += B[i + k][j];
                        cnt2[i][j] += cnt[i][j];
                    }
                }
                res[i][j] /= cnt2[i][j];
                if (res[i][j] <= t) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}

/*
import java.util.Scanner;

public class Main {
    //运行超时，70
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), L = scanner.nextInt(), r = scanner.nextInt(), t = scanner.nextInt();
        int[][] A = new int[n][n];
        double[][] B = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = -r; k <= r; k++) {
                    for (int l = -r; l <= r; l++) {
                        if ((i + k >= 0 && i + k < n) && (j + l >= 0 && j + l < n)) {
                            cnt++;
                            B[i][j] += A[i + k][j + l];
                        }
                    }
                }
                B[i][j] /= cnt;
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (B[i][j] <= t) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
*/
