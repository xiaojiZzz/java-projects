package ccf_csp._2020._09;

import java.util.Scanner;

public class _2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int t = scanner.nextInt();
        int xl = scanner.nextInt();
        int yd = scanner.nextInt();
        int xr = scanner.nextInt();
        int yu = scanner.nextInt();
        int[][] location = new int[n][2 * t];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * t; j++) {
                location[i][j] = scanner.nextInt();
            }
        }
        int res1 = 0, res2 = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            boolean flag = false;
            for (int j = 0; j < 2 * t; j += 2) {
                if (location[i][j] >= xl && location[i][j] <= xr && location[i][j + 1] >= yd && location[i][j + 1] <= yu) {
                    flag = true;
                    cnt++;
                } else {
                    cnt = 0;
                }
                if (cnt >= k) {
                    res2++;
                    break;
                }
            }
            if (flag) {
                res1++;
            }
        }
        System.out.println(res1);
        System.out.println(res2);
    }
}
