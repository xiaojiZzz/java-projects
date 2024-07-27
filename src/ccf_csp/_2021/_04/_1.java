package ccf_csp._2021._04;

import java.util.Scanner;

public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt(), L = scanner.nextInt();
        int[][] gra = new int[n][m];
        int[] res = new int[L];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gra[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[gra[i][j]]++;
            }
        }
        for (int i = 0; i < L; i++) {
            System.out.print(res[i] + " ");
        }
    }
}
