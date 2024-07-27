package ccf_csp._2023._12;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int k = 0;
                    for (k = 0; k < m; k++) {
                        if (arr[i][k] >= arr[j][k]) {
                            break;
                        }
                    }
                    if (k == m) {
                        ans[i] = j + 1;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }
}
