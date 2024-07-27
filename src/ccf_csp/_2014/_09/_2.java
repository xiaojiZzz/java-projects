package ccf_csp._2014._09;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[][] table = new boolean[101][101];
        int[][] rectangle = new int[n][4];
        for (int i = 0; i < n; i++) {
            rectangle[i][0] = scanner.nextInt();
            rectangle[i][1] = scanner.nextInt();
            rectangle[i][2] = scanner.nextInt();
            rectangle[i][3] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];
            for (int j = x1 + 1; j <= x2; j++) {
                for (int k = y1 + 1; k <= y2; k++) {
                    table[j][k] = true;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (table[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
